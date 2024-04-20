package com.ding.hyld.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.EmailCodeInfo;
import com.ding.hyld.info.UserInfo;
import com.ding.hyld.mapper.UserMapper;
import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.service.BaseService;
import com.ding.hyld.service.EmailCodeService;
import com.ding.hyld.service.LoginService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.RsaUtils;
import com.ding.hyld.utils.TimeUtils;
import com.ding.hyld.vo.EmailCodeVo;
import com.ding.hyld.vo.LoginUserVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService, BaseService {
    @Autowired
    LoginService loginService;
    @Autowired
    EmailCodeService emailCodeService;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public User login(String account, String password) {
        return baseMapper.login(account,password);
    }

    /**
     * 1.token 合法性校验
     *   若为空 判断 redis 是否存在,不存在返回错误
     *   存在但校验失败,返回错误
     *   存在且校验成功,返回结果 loginUserVo
     * @param token
     * @return
     */
    @Override
    public R findUserByToken(String token) {
        CurrentUser currentUser= loginService.checkToken(token);
        if (currentUser==null){
            return R.fail("token 不合法!");
        }
        LoginUserVo loginUserVo=new LoginUserVo();
        loginUserVo.setId(currentUser.getUser().getId());
        loginUserVo.setName(currentUser.getUser().getName());
        return R.success(loginUserVo);
    }

    @Override
    public void register(UserVo userVo) {
        try {
            userVo.setPassword(new BCryptPasswordEncoder().encode(RsaUtils.decryptByPrivateKey(userVo.getPassword())+ CommonCode.SLAT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userVo.setCoin(3200);
        baseMapper.register(userVo);
    }

    @Override
    public User findByName(String name) {
        return baseMapper.findByName(name);
    }

    @Override
    public UserInfo findById(Integer id) {
        delay(id);  // 查看个人信息重置token生命周期为1天
        return baseMapper.findById(id);
    }

    @Override
    public void updateUserInfo(UserVo userVo) {
        baseMapper.updateUserInfo(userVo);
    }

    @Override
    public List<UserInfo> searchUser(Page page, UserVo userVo) {
        return baseMapper.searchUser(page, userVo);
    }

    @Override
    public void saveUser(UserVo userVo) {
        baseMapper.saveUser(userVo);
    }

    @Override
    public void saveUserPassword(UserVo userVo) {
        try {
            String s = RsaUtils.decryptByPrivateKey(userVo.getPassword())+ CommonCode.SLAT;
            userVo.setPassword(new BCryptPasswordEncoder().encode(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseMapper.saveUserPassword(userVo);
    }

    @Override
    public void saveHeadPortrait(UserVo userVo) {
        baseMapper.saveHeadPortrait(userVo);
    }

    /**
     * 增加经验
     * @param userId 用户
     * @param ex 经验值
     */
    @Override
    public void addEx(Integer userId, int ex) {
        baseMapper.addEx(userId, ex);
    }

    @Override
    public User findByQqOpenId(String qqOpenId) {
        return baseMapper.findByQqOpenId(qqOpenId);
    }

    @Override
    public User findByEmail(String email) {
        return baseMapper.findByEmail(email);
    }

    @Override
    public R bindEmail(UserVo userVo) {
        EmailCodeVo emailCodeVo = new EmailCodeVo();
        emailCodeVo.setUserId(userVo.getId());
        emailCodeVo.setCode(userVo.getEmailCode());
        emailCodeVo.setEmail(userVo.getEmail());
        emailCodeVo.setStart(TimeUtils.getBeforeTheSpecifiedTimeStr(0,0,0,0,-1,0,TimeUtils.FORMAT_3));
        List<EmailCodeInfo> infos = emailCodeService.findBy(emailCodeVo);
        if(infos==null || infos.size()!=1){ // 根据 userid+邮箱+验证码+1分钟内 未找到相关记录
            return R.fail("邮箱绑定失败!请重新绑定!");
        }
        baseMapper.bindEmail(userVo);
        return R.success("已成功绑定邮箱!");
    }

    @Override
    public void unbindEmail(Integer userId) {
        baseMapper.unbindEmail(userId);
    }

    @Override
    public void updatePassword(User user) {
        baseMapper.updatePassword(user);
    }

    @Override
    public Integer searchUserOfPage(UserVo userVo) {
        return baseMapper.searchUserOfPage(userVo);
    }

    @Override
    public void updateCoin(UserVo userVo) {
        baseMapper.updateCoin(userVo);
    }

    @Override
    public UserInfo findBriefInfoById(Integer id) {
        return baseMapper.findBriefInfoById(id);
    }

    /**
     * 项目启动时立即执行一次
     * @Scheduled(fixedRate = 30*60*1000)  // 30分钟执行一次,单位为毫秒
     *
     * cron
     * @Scheduled(cron = "0 0/30 9-22 * * ?")  // 每天在9点至22点之间,0分和30分执行一次
     */

    /**
     * 重置用户的token生命周期
     * @param userId
     */
    @Override
    public void delay(Integer userId) {
        redisTemplate.expire("login_"+userId,1, TimeUnit.DAYS);
    }

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void giftCoin(){  // 每天0点为每位用户赠送60代币,当用户拥有的代币大于8000则不赠送
        baseMapper.giftCoin();
    }


}
