package com.ding.hyld.controller;

import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.DictionaryInfo;
import com.ding.hyld.info.UserInfo;
import com.ding.hyld.service.LoginService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public R login(UserVo userVo){
        return loginService.login(userVo, 1, TimeUnit.DAYS);
    }


    @PostMapping("/logout")
    public R logout(){
        loginService.logout();
        return R.success("已安全退出!(●'◡'●)");
    }

    @PostMapping("/register")
    public R register(@RequestBody UserVo userVo){
        User user=userService.findByName(userVo.getAccount());
        if(user!=null){
            return R.fail("该用户名已被注册!");
        }
        userService.register(userVo);
        return R.success("注册成功!");
    }

    @PreAuthorize("hasAuthority('test')")
    @GetMapping("/getCurrentUserInfo")
    public R getCurrentUserInfo(){
        return R.success(userService.findById(getUserId()));
    }

    @PostMapping("/updateUserInfo")
    public R updateUserInfo(@RequestBody UserVo userVo){ // 用户修改自己的信息
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
