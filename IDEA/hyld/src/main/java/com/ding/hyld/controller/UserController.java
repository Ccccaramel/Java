package com.ding.hyld.controller;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.constant.SystemConfigKey;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.DictionaryInfo;
import com.ding.hyld.info.SystemConfigInfo;
import com.ding.hyld.info.UserInfo;
import com.ding.hyld.service.LoginService;
import com.ding.hyld.service.SystemConfigService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.service.VisitLogService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.RsaUtils;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.QQUserVo;
import com.ding.hyld.vo.UserVo;
import com.ding.hyld.vo.VisitLogVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    private VisitLogService visitLogService;
    /**
     * 第三方(注册并)登录
     * @param userVo
     * @return
     */
    @PostMapping("/qqLogin")
    public R qqLogin(UserVo userVo, String data,QQUserVo qqUserVo){
        VisitLogVo visitLogVo = new VisitLogVo();
        try {
            visitLogVo = JSON.parseObject(RsaUtils.decryptByPrivateKey(data), VisitLogVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginService.qqLogin(userVo,visitLogVo, qqUserVo);
    }

    @PostMapping("/login")
    public R login(UserVo userVo, String data){
        VisitLogVo visitLogVo = new VisitLogVo();
        try {
            visitLogVo = JSON.parseObject(RsaUtils.decryptByPrivateKey(data), VisitLogVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            result.put("totalPage",Math.ceil(userService.searchUser(null,userVo).size()*1.0/page.getSize()));
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
}
