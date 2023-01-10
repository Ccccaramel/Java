package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.EmailCode;
import com.ding.hyld.info.EmailCodeInfo;
import com.ding.hyld.mapper.EmailCodeMapper;
import com.ding.hyld.service.EmailCodeService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.EmailCodeVo;
import com.ding.hyld.vo.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Slf4j
@Service
public class EmailCodeServiceImpl extends ServiceImpl<EmailCodeMapper, EmailCode> implements EmailCodeService {

    @Autowired(required = false)
    private JavaMailSender javaMailSender; // 引入Spring Mail依赖后，会自动装配到IOC容器。用来发送邮件
    @Autowired(required = false)
    private MailProperties mailProperties;

    @Override
    public List<EmailCodeInfo> searchEmailCode(Page page, EmailCodeVo emailCodeVo) {
        return baseMapper.searchEmailCode(page, emailCodeVo);
    }

    @Override
    public void add(EmailCodeVo emailCodeVo) {
        baseMapper.add(emailCodeVo);
    }

    @Override
    public R sendEmailCode(EmailCodeVo emailCodeVo) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 邮件发送来源
            mimeMessageHelper.setFrom(emailCodeVo.getFormEmail());
            // 邮件发送目标
            mimeMessageHelper.setTo(emailCodeVo.getEmail());
            // 设置标题
            mimeMessageHelper.setSubject(emailCodeVo.getSubject());
            // 设置内容，并设置内容 html 格式为 true
            mimeMessageHelper.setText(emailCodeVo.getText(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            R.fail("验证码发送失败,请联系管理员〒▽〒");
        }
        add(emailCodeVo);
        return R.success("验证码已发送,请留意查收( •̀ ω •́ )y");
    }

    @Override
    public List<EmailCodeInfo> findBy(EmailCodeVo emailCodeVo) {
        return baseMapper.findBy(emailCodeVo);
    }
}
