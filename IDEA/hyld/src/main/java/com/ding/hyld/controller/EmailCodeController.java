package com.ding.hyld.controller;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.EmailCodeInfo;
import com.ding.hyld.service.EmailCodeService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.service.VisitLogService;
import com.ding.hyld.utils.*;
import com.ding.hyld.vo.EmailCodeVo;
import com.ding.hyld.vo.VisitLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/emailCode")
public class EmailCodeController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailCodeService emailCodeService;

    @Autowired
    private VisitLogService visitLogService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @PostMapping("/sendEmailCode")
    public R sendEmailCode(@RequestBody EmailCodeVo emailCodeVo) {
        String userName = "";
        Integer userId = 0;
        if(isLogin()){
            userId = getUserId();
            userName = getUserName();
        }else{
            User user = userService.findByEmail(emailCodeVo.getEmail());
            if(user==null){
                return R.fail("请检查邮箱是否填写正确!");
            }
            userName = user.getName();
            userId = user.getId();
        }

        VisitLogVo visitLogVo = new VisitLogVo();
        try {
            visitLogVo = JSON.parseObject(RsaUtils.decryptByPrivateKey(emailCodeVo.getData()), VisitLogVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        emailCodeVo.setCode(EmailUtils.getEmailCode()); // 验证码
        emailCodeVo.setUserId(userId);
        emailCodeVo.setUserName(userName);
        emailCodeVo.setFormEmail(CommonCode.FORM_EMAIL);
        emailCodeVo.setSubject("【164office】用户邮箱验证");
        emailCodeVo.setIp(visitLogVo.getIp());
        emailCodeVo.setAddress(visitLogVo.getTrueAddress());
        try {
            emailCodeVo.setFingerprint(RsaUtils.decryptByPrivateKey(emailCodeVo.getFingerprint()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String text = "<html>\n" +
                "\t<body>\n" +
                "\t\t<br>\n" +
                "\t\t<h1>以下是您的验证码：</h1>\n" +
                "\t\t<h2 style=\"color: #66ccff;\">&emsp;"+emailCodeVo.getCode()+"</h2>\n" +
                "\t\t<hr/>\n" +
                "\t\t<h3>尊敬的用户<span style=\"color: #FFB6C1;\">"+emailCodeVo.getUserName()+"</span>，您好：</h3>\n" +
                "\t\t<h3>&emsp;我们（164office.cn-荒野社区平台）收到了来自您的"+emailCodeVo.getTitle()+"请求，请使用上面的验证码验证您的账号归属。</h3>\n" +
                "\t\t<h3>&emsp;该验证码将在1分钟后过期，请及时输入。</h3>\n" +
                "\t\t<br>\n" +
                "\t\t<h3>&emsp;为保证账号安全，请勿泄漏此验证码。</h3>\n" +
                "\t\t<h3>&emsp;祝在【164office】收获愉快！</h3>\n" +
                "\t\t<h3>&emsp;(〃￣︶￣)人(￣︶￣〃)</h3>\n" +
                "\t\t<hr/>\t\n" +
                "\t\t<h3>如果您有任何疑问请<a href='https://www.164office.cn'>访问平台</a>留言询问</h3>\n" +
                "\t</body>\n" +
                "</html>";
        emailCodeVo.setText(text);
        return emailCodeService.sendEmailCode(emailCodeVo);
    }

    @PostMapping("/emailVerify")
    public R emailVerify(@RequestBody EmailCodeVo emailCodeVo) {

        // 日志
        VisitLogVo visitLogVo = new VisitLogVo();
        try {
            visitLogVo = JSON.parseObject(RsaUtils.decryptByPrivateKey(emailCodeVo.getData()), VisitLogVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        emailCodeVo.setStart(TimeUtils.getBeforeTheSpecifiedTimeStr(0,0,0,0,-1,0,TimeUtils.FORMAT_3)); // 1分钟内
        List<EmailCodeInfo> infos = emailCodeService.findBy(emailCodeVo);
        if(infos==null || infos.size()!=1){ // 根据 userid+邮箱+验证码+1分钟内 未找到相关记录或找到多条记录
            // 并标记验证码失效
            visitLogVo.setNote("邮箱验证-找回账号|重置密码-验证码错误!");
            visitLogService.add(visitLogVo);
            return R.fail("验证码无效!请在1分钟之后重新发送验证码!");
        }




        User user = userService.findByEmail(emailCodeVo.getEmail());
        if(user==null){
            return R.fail("此邮箱未绑定用户!");
        }
        visitLogVo.setNote("邮箱验证-找回账号|重置密码-验证成功(用户id:"+user.getId()+")!");
        visitLogService.add(visitLogVo);

        // 认证通过,使用 userId 生成一个 jwt/token
        String token = JWTUtils.createToken(Long.valueOf(user.getId()));
        // 把用户信息存入 redis , userId 作为 key
        redisTemplate.opsForValue().set("emailToken_"+token, JSON.toJSONString(user),1, TimeUnit.MINUTES); // 1分钟后过期

        // 返回一个 emailToken 修改密码时需要传入
        return R.success(token,"验证通过!");
    }

}
