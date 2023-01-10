package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.EmailCode;
import com.ding.hyld.info.EmailCodeInfo;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.EmailCodeVo;
import com.ding.hyld.vo.Page;

import java.util.List;

public interface EmailCodeService extends IService<EmailCode> {
    List<EmailCodeInfo> searchEmailCode(Page page, EmailCodeVo emailCodeVo);

    void add(EmailCodeVo emailCodeVo);

    R sendEmailCode(EmailCodeVo emailCodeVo);

    List<EmailCodeInfo> findBy(EmailCodeVo emailCodeVo);
}
