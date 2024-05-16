package com.ding.office.controller;

import com.alibaba.fastjson.JSON;
import com.ding.office.constant.CommonCode;
import com.ding.office.constant.SystemConfigKey;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.entity.User;
import com.ding.office.info.SystemConfigInfo;
import com.ding.office.service.LoginService;
import com.ding.office.service.SystemConfigService;
import com.ding.office.service.UserService;
import com.ding.office.service.impl.QQIPServiceImpl;
import com.ding.office.utils.IpUtils;
import com.ding.office.utils.R;
import com.ding.office.utils.RsaUtils;
import com.ding.office.vo.Page;
import com.ding.office.vo.QQUserVo;
import com.ding.office.vo.UserVo;
import com.ding.office.vo.VisitLogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private QQIPServiceImpl ibsService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 第三方(注册并)登录
     * @param userVo
     * @return
     */
    @PostMapping("/qqLogin")
    public R qqLogin(UserVo userVo, String data,QQUserVo qqUserVo, HttpServletRequest request){
        VisitLogVo visitLogVo = new VisitLogVo();

        String ip = IpUtils.getIpAddress(request);
        visitLogVo.setIp(ip);
        Map<String, String> addressInfo = ibsService.getAddress(ip);
        visitLogVo.setTrueAddress(addressInfo.get("trueAddress"));
        visitLogVo.setAddress(addressInfo.get("address"));

        return loginService.qqLogin(userVo,visitLogVo, qqUserVo);
    }

    @PostMapping("/login")
    public R login(UserVo userVo, HttpServletRequest request){
        VisitLogVo visitLogVo = new VisitLogVo();
        String ip = IpUtils.getIpAddress(request);
        visitLogVo.setIp(ip);
        Map<String, String> addressInfo = ibsService.getAddress(ip);
        visitLogVo.setTrueAddress(addressInfo.get("trueAddress"));
        visitLogVo.setAddress(addressInfo.get("address"));

        return loginService.login(userVo, 1, TimeUnit.DAYS,visitLogVo);
    }


    @PostMapping("/logout")
    public R logout(){
        loginService.logout();
        return R.success("已安全退出!(●'◡'●)");
    }

    @PostMapping("/register")
    public R register(@RequestBody UserVo userVo){
        log.info("传入参数 userVo:{}",userVo.toString());
        SystemConfigInfo systemConfigInfo = systemConfigService.findByKey(SystemConfigKey.ALLOW_REGISTRATION);
        if(systemConfigInfo.getV().equals("0")){
            return R.fail("系统已禁止新用户注册!");
        }

        /**
         * 限制一台机器只能注册一个账号
         */
        UserVo conditionVo = new UserVo();
        try {
            String fingerprint = RsaUtils.decryptByPrivateKey(userVo.getNo());
            log.info("fingerprint:{}",fingerprint);
            conditionVo.setFingerprint(fingerprint);
            userVo.setFingerprint(fingerprint);
            log.info("userVo:{}",userVo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        List<UserInfo> userInfoList = userService.searchUser(null, conditionVo);
//        log.info("userInfoList:{}",userInfoList);
//        if(userInfoList.size()>0){
//            return R.fail("注册超过限定次数!");
//        }


        User user=userService.findByName(userVo.getAccount()); // searchUser 中是 like
        if(user!=null){
            return R.fail("该用户名已被注册!");
        }
        userService.register(userVo);
        return R.success("注册成功!");
    }

//    @PreAuthorize("hasAuthority('test')")
    @GetMapping("/getCurrentUserInfo")
    public R getCurrentUserInfo(){
        return R.success(userService.findById(getUserId()));
    }

    /**
     * 用户修改自己的信息
     * @param userVo
     * @return
     */
    @PreAuthorize("hasAuthority('userInfoManage_update')")
    @PostMapping("/updateUserInfo")
    public R updateUserInfo(@RequestBody UserVo userVo){
        userService.updateUserInfo(userVo);
         return R.success("个人基本信息修改成功!");
    }

    @GetMapping("/searchUser")
    public R searchUser(Page page, UserVo userVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",userService.searchUser(page, userVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(userService.searchUserOfPage(userVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 管理员在【用户管理】菜单中修改用户信息
     * @param userVo
     * @return
     */
    @PreAuthorize("hasAuthority('userManage_update')")
    @PostMapping("/saveUser")
    public R saveUser(@RequestBody UserVo userVo){
        User user=userService.findByName(userVo.getName()); // 查询时发现结果忽略了大小写,相关内容详见 Database > mysql.txt
        if(user!=null && !userVo.getId().equals(user.getId())){
            return R.fail("该用户名已被注册!");
        }
        userService.saveUser(userVo);
        return R.success("用户信息修改成功!");
    }

    @GetMapping("/checkToken")
    public R checkToken(){
        return R.success();
    }

    @PreAuthorize("hasAnyAuthority('userInfoManage_updatePassword','userManage_updatePassword')")
    @PostMapping("/saveUserPassword")
    public R saveUserPassword(@RequestBody UserVo userVo){
        userService.saveUserPassword(userVo);
        return R.success("用户密码修改成功!");
    }

    @PostMapping("/saveHeadPortrait")
    public R saveHeadPortrait(@RequestBody UserVo userVo){
        userVo.setId(getUserId());
        userService.saveHeadPortrait(userVo);
        return R.success("已成功更换头像!");
    }

    /**
     * 绑定邮箱
     *   在一分钟之内,绑定后解绑再使用同一验证码绑定是允许的,没有限制,暂时没有想到这样会有什么问题
     * @param userVo
     * @return
     */
    @PostMapping("/bindEmail")
    public R bindEmail(@RequestBody UserVo userVo){
        userVo.setId(getUserId());
        return userService.bindEmail(userVo);
    }

    @PostMapping("/unbindEmail")
    public R unbindEmail(){
        userService.unbindEmail(getUserId());
        return R.success("已成功解绑邮箱!");
    }

    @PostMapping("/updatePassword")
    public R updatePassword(@RequestBody UserVo userVo) {
//        baseMapper.unbindEmail(userId);
        String emailToken = userVo.getEmailToken();
        if(StringUtils.hasText(emailToken) && Boolean.TRUE.equals(redisTemplate.hasKey("emailToken_" + emailToken))){ // emailToken 存在
            User user=JSON.parseObject(redisTemplate.opsForValue().get("emailToken_" + emailToken),User.class);
            try {
                if(user==null){
                    return R.fail("系统错误,请重新进行验证操作!");
                }
                user.setPassword(new BCryptPasswordEncoder().encode(RsaUtils.decryptByPrivateKey(userVo.getPassword()) + CommonCode.SLAT));
            } catch (Exception e) {
                e.printStackTrace();
            }
            userService.updatePassword(user);
            return R.success("密码修改成功!");
        }
        return R.fail("密码修改失败!");
    }

    @GetMapping("/getCoin")
    public R getCoin(){
        if(isLogin()){
            return R.success(userService.findById(getUserId()).getCoin());
        }
        return R.fail("用户未登录,获取用户代币失败");
    }

    @PostMapping("/generate")
    public R generate(@RequestParam Integer currentStep,@RequestParam Integer[] bet){
        log.info("currentStep:{}",currentStep);
        log.info("bet:{}",bet);
        HashMap<String,Object> result=new HashMap<>();
        if(isLogin()){
            UserVo userVo=new UserVo();
            int betSum=0;  // 押注
            int magic=0;
            boolean accident=Math.random() < 0.64;  // 是否触发特殊机制
//            boolean accident=true;  // 是否触发特殊机制
            int randomStep= (int) (22 * 6 + Math.floor(Math.random() * 22));  // 前进步数
            result.put("randomStep",randomStep);  // 移动步数
            int lastStep=((currentStep+randomStep)%22+4)%22;  // 最终位置
            log.info("lastStep:{}",lastStep);
            int[] magnification = {10,10,25,50,5,2,10,   10,2,5,2,   10,10,2,20,5,2,10,   20,2,5,2};  // 倍率
            int[] role = {6,4,0,0,7,7,5,   3,3,7,6,   6,4,1,1,7,5,5,   2,2,7,4};  // 对应角色
            int userCoin=userService.findById(getUserId()).getCoin();

            result.put("accident",accident);  // 默认允许启动
            result.put("firing",true);  // 默认允许启动

            for(int i=0;i<bet.length;i++){
                betSum+=bet[i];
            }

            userVo.setId(getUserId());

            if(userCoin<betSum){  // 用户的总代币小于押注
                result.put("firing",false);  // 禁止启动
                result.put("info","代币不够,无法启动(T_T)");
                return R.fail(result);
            }

            if(accident){
                magic= (int) (Math.floor(Math.random()*10)+1);
//                magic = 8;
                result.put("magic",magic);

                if(magic==2){
                    int t=0;
                    for(int i=0;i<bet.length;i++){
                        t+=bet[i];
                        bet[i]=0;
                    }
                    for(int i=0;i<3;){
                        int j= (int) (Math.floor(Math.random()*8)+1);
                        if(bet[j]==0){
                            bet[j]= (int) Math.floor(t/3);
                            i++;
                        }
                    }
                    log.info("---押注重新随机分配3份!---");
                }
                else if(magic==7){
                    for(int i=0;i<bet.length;i++){
                        if(bet[i]>30){
                            bet[i]=0;
                        }
                    }
                    log.info("---单个押注大于30置0!---");
                }
                result.put("bet",bet);  // 返回重置后的押注

                if(magic!=5){
                    // 扣除押注
                    userCoin-=betSum;
                }
                else{
                    log.info("---免押注!---");
                }

                if(magic==3){
                    userVo.setCoin(userCoin);
                    userService.updateCoin(userVo); // 更新代币
                    result.put("info","吞押注并终止!");
                    result.put("firing",false);  // 禁止启动
                    log.info("---吞押注并终止!---");
                    return R.fail(result);
                }
            }
            else{  // 不触发特殊机制,普通运行
                userCoin-=betSum;
            }

            // 结算
            int settleAccounts;
            if(magic==4){
                int mix=100;
                for(int i=0;i<bet.length;i++){
                    if(bet[i]<mix&&bet[i]!=0){
                        mix=bet[i];
                    }
                }
                settleAccounts=mix*magnification[lastStep];
                log.info("---注数最小!---");
            }
            else{
                settleAccounts=bet[role[lastStep]]*magnification[lastStep];  // 注数*倍率
            }
            if(magic==1&&bet[1]==0){
                settleAccounts/=2;
                log.info("---奖励减半!---");
            }
            else if(magic==6&&role[lastStep]==1){
                settleAccounts*=2;
                log.info("---奖励翻倍!---");
            }
            if(magic==8){
                ArrayList<Integer> extra = new ArrayList<>();
                extra.add(lastStep);
                for (int i = 0; i < 2;){
                    int es = (int) ((Math.floor(Math.random() * 20 + 1)+lastStep)%22);
                    log.info("es:{}",es);
                    if (!extra.contains(es)) {
                        extra.add(es);
                        settleAccounts+=bet[role[es]]*magnification[es];
                        i++;
                    }
                }
                result.put("extra",extra.toArray());  // 收益
                log.info("---本轮额外命中2个窗口!---");
            }
            else if(magic==9){
                settleAccounts*=1.5;
                log.info("---奖励变为1.5倍!---");
            }
            else if (magic == 10) {
                int n = 0;
                for (int i = 0; i < bet.length;i++){
                    if (bet[i]>0) {
                        n++;
                    }
                }
                if (n > 5) {
                    settleAccounts-=1600;
                    log.info("---扣除1600!---");
                }
            }
            log.info("奖励:{}",settleAccounts);
            result.put("settleAccounts",settleAccounts);  // 收益
            userCoin += settleAccounts;

            userVo.setCoin(userCoin);
            userService.updateCoin(userVo);  // 更新代币

            return R.success(result);
        }
        else{
            result.put("firing",true);  // 允许启动
            result.put("info","用户未登录,结果生成中止,由前端生成");
            return R.fail(result);
        }
    }
}
