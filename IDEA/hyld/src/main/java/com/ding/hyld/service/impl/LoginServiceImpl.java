package com.ding.hyld.service.impl;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.constant.SystemConfigKey;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.SystemConfigInfo;
import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.service.*;
import com.ding.hyld.utils.JWTUtils;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.RsaUtils;
import com.ding.hyld.vo.QQUserVo;
import com.ding.hyld.vo.UserVo;
import com.ding.hyld.vo.VisitLogVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private QQUserService qqUserService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private VisitLogService visitLogService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 1.检查参数是否合法
     * 2.根据用户户名和密码去 user 表中查询是否存在
     *   a.不存在,登录失败
     *   b.存在,使用 JWT 生成 token 返回给前端
     * 3.token 存入 redis 中
     *   redis 以键值对 token:user 保存 user 信息,并设置过期时间
     * 4.双重保障
     *   登录时先认证 token 是否合法,再去 redis 认证是否存在
     *
     * @param userVo
     * @return
     */
    @Override
    public R login(UserVo userVo, long timeout, TimeUnit unit, VisitLogVo visitLogVo) {
        log.info("登录用户信息:"+userVo.toString());

        // 判断是否存在该用户,如果存在,那么获取该用户指定条件下登录失败的次数
        User userInfo = userService.findByName(userVo.getAccount());
        if(userInfo!=null){
            // 获取该 用户+今日+相同IP+登录失败 总次数
            VisitLogVo searchVo = new VisitLogVo();
            searchVo.setUserId(userInfo.getId());
            LocalDateTime localDateTime=LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            searchVo.setStartDate(dateTimeFormatter.format(localDateTime.withHour(0).withMinute(0).withSecond(0)));
            searchVo.setIp(visitLogVo.getIp());
            searchVo.setNote("登录失败");
            if(visitLogService.searchVisitLog(null,searchVo).size()>=3){
                visitLogVo.setUserId(userInfo.getId());
                visitLogVo.setNote("登录限制-登录失败次数已达3次");
                visitLogService.add(visitLogVo);
                return R.fail("用户名或密码错误!失败次数已达3次!");
            }
        }


        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
            authenticationToken = new UsernamePasswordAuthenticationToken(userVo.getAccount(), RsaUtils.decryptByPrivateKey(userVo.getPassword()) + CommonCode.SLAT); // 解密 > +盐
        } catch (Exception e) {
            e.printStackTrace();
        }
        Authentication authenticate;
        try{
            authenticate = authenticationManager.authenticate(authenticationToken);
        }catch (Exception e1){
            User user = userService.findByName(userVo.getAccount());
            if(user!=null){
                visitLogVo.setUserId(user.getId());
            }
            visitLogVo.setNote("登录失败-用户名或密码错误");
            visitLogService.add(visitLogVo);
            return R.fail("用户名或密码错误!");
        }

        CurrentUser currentUser = (CurrentUser)authenticate.getPrincipal();
        Integer status = currentUser.getUser().getStatus();
        visitLogVo.setUserId(currentUser.getUser().getId());
        if(status.equals(DictionaryCode.USER_STATUS_2)){
            visitLogVo.setNote("登录失败-已冻结用户尝试登录");
            visitLogService.add(visitLogVo);
            return R.fail("用户已被冻结!");
        }
        else if(status.equals(DictionaryCode.USER_STATUS_3)){
            visitLogVo.setNote("登录失败-已注销用户尝试登录");
            visitLogService.add(visitLogVo);
            return R.fail("用户不存在!");
        }

        SystemConfigInfo systemConfigInfo = systemConfigService.findByKey(SystemConfigKey.ALLOW_LOGIN);
        if(currentUser.getUser().getRole().equals(DictionaryCode.USER_ROLE_2) && systemConfigInfo.getV().equals("0")){
            visitLogVo.setNote("登录管控-系统已禁止用户登录");
            visitLogService.add(visitLogVo);
            return R.fail("系统已禁止用户登录!");
        }

        // 认证通过,使用 userId 生成一个 jwt/token
        String token = JWTUtils.createToken(Long.valueOf(currentUser.getUser().getId()));

        // 把完整用户信息存入 redis , userId 作为 key
        redisTemplate.opsForValue().set("login_"+currentUser.getUser().getId(), JSON.toJSONString(currentUser)); // 1天后过期

        Map<String,Object> res = new HashMap<>();
        res.put("token",token);
        try {
            res.put("power",currentUser.getPermissions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!StringUtils.hasText(visitLogVo.getNote())){
            visitLogVo.setNote("账号登录");
        }
        visitLogService.add(visitLogVo);
        return R.success(res,"欢迎回来!(*^▽^*)");
    }

    /**
     * 通过qq注册+登录
     *   1.根据 openid 查找 user
     *     存在:获取 user 并调用登录方法
     *     不存在:根据参数注册新用户,并调用登录方法
     *
     * @param userVo
     * @return
     */
    @Override
    public R qqLogin(UserVo userVo, VisitLogVo visitLogVo,QQUserVo qqUserVo) {
        log.info("userVo:"+userVo.toString());
        User user=userService.findByQqOpenId(userVo.getQqOpenId());
        if(user==null){ // qqOpenId 不存在,是新用户,为他注册新账号
            if(userService.findByName(userVo.getAccount())!=null){ // 检查昵称是否已被注册, searchUser 中是 like
                return R.fail("用户名已被注册〒▽〒");
            }
            /**
             * 使用QQ登录成功后会自动通过 openid 进行登录,但如果是新用户(并未注册),那么根据 openid 是无法找到用户的并且密码是空的,直接返回,等待用户设置密码
             * 新用户设置好符合的昵称和密码后再次注册+登录,此时根据 openid 依旧无法找到用户,但(传入参数)密码是存在的,符合注册新用户条件,并为他注册账号
             */
            if(!StringUtils.hasText(userVo.getPassword())){
                return R.fail("未输入密码");
            }

            /**
             * 注册
             *   1.是否允许注册新用户
             *   2.检查用户名
             */
            log.info("传入参数 userVo:{}",userVo);
            SystemConfigInfo systemConfigInfo = systemConfigService.findByKey(SystemConfigKey.ALLOW_REGISTRATION);
            if(systemConfigInfo.getV().equals("0")){
                return R.fail("系统已禁止新用户注册!");
            }

            try {
                String fingerprint = RsaUtils.decryptByPrivateKey(userVo.getNo());
                userVo.setFingerprint(fingerprint);
                log.info("qq新用户 userVo:{}",userVo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            userService.register(userVo); // 保存新用户信息
            qqUserService.qqUserInfoRecord(qqUserVo);// 保存用户的QQ账号基本信息
            user=userService.findByQqOpenId(userVo.getQqOpenId()); // 重新获取该用户信息
        }

        /**
         * 登录
         *   1.根据 qqOpenId 获取用户信息
         */
//        UserVo loginVo = new UserVo();
//        loginVo.setAccount(user.getName());
//        loginVo.setPassword(user.getPassword());
        visitLogVo.setNote("QQ登录");

        /**
         * 检查是否允许登录
         */
        if(user.getStatus().equals(DictionaryCode.USER_STATUS_2)){
            visitLogVo.setNote("登录失败-已冻结用户尝试登录");
            visitLogService.add(visitLogVo);
            return R.fail("用户已被冻结!");
        }
        else if(user.getStatus().equals(DictionaryCode.USER_STATUS_3)){
            visitLogVo.setNote("登录失败-已注销用户尝试登录");
            visitLogService.add(visitLogVo);
            return R.fail("用户不存在!");
        }

        SystemConfigInfo systemConfigInfo = systemConfigService.findByKey(SystemConfigKey.ALLOW_LOGIN);
        if(user.getRole().equals(DictionaryCode.USER_ROLE_2) && systemConfigInfo.getV().equals("0")){
            visitLogVo.setNote("登录管控-系统已禁止用户登录");
            visitLogService.add(visitLogVo);
            return R.fail("系统已禁止用户登录!");
        }

        // 认证通过,使用 userId 生成一个 jwt/token
        String token = JWTUtils.createToken(Long.valueOf(user.getId()));

//        log.info("1111");
//        UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(userVo.getAccount(), null);
//        log.info("2222");
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        log.info("3333");
//        CurrentUser currentUser = (CurrentUser)authenticate.getPrincipal();
//        log.info("4444");

        CurrentUser currentUser = new CurrentUser(user,menuService.getCurrentUserPower(user.getRole()));

        // 把完整用户信息存入 redis , userId 作为 key
        redisTemplate.opsForValue().set("login_"+user.getId(), JSON.toJSONString(currentUser)); // 1天后过期

        Map<String,Object> res = new HashMap<>();
        res.put("token",token);
        try {
            res.put("power",currentUser.getPermissions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!StringUtils.hasText(visitLogVo.getNote())){
            visitLogVo.setNote("账号登录");
        }
        visitLogService.add(visitLogVo);
        return R.success(res,"欢迎回来!(*^▽^*)");
    }

    @Override
    public CurrentUser checkToken(String token) {
        if(token==null){
            return null;
        }
        Map<String,Object> objectMap = JWTUtils.checkToken(token);
        if(objectMap==null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("login_" + objectMap.get("userId"));
        if(userJson==null){
            return null;
        }
        CurrentUser currentUser=JSON.parseObject(userJson,CurrentUser.class); // 将字符串转换为对象
        return currentUser;
    }

    @Override
    public void logout() {
        // 获取 SecurityContextHolder 中的用户ID
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        CurrentUser currentUser = (CurrentUser)authentication.getPrincipal();
        Integer userId=currentUser.getUser().getId();
//        String token = JWTUtils.createToken(Long.valueOf(userId));

        // 删除 redis 中的值
        redisTemplate.delete("login_"+userId);
    }

    /**
     * 另一种加密方式
     * @param args
     */
    public static void main(String[] args) {
        String pw = "123" + CommonCode.SLAT;

        System.out.println(DigestUtils.md2Hex(pw));
        System.out.println(DigestUtils.md2Hex(pw));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String s1 = passwordEncoder.encode(pw);
        String s2 = passwordEncoder.encode(pw);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(passwordEncoder.matches(pw,s2));

        String s3 = "$2a$10$LsHwzUefUw.9T7nN2JCzkugAxmK7CWCAxUcS5QhPF9R2YbNyrEk3C";
        System.out.println(passwordEncoder.matches(pw,s3));
    }
}
