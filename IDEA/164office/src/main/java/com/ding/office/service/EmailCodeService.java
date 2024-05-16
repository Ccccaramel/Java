package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.EmailCode;
import com.ding.office.info.EmailCodeInfo;
import com.ding.office.utils.R;
import com.ding.office.vo.EmailCodeVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface EmailCodeService extends IService<EmailCode> {
    List<EmailCodeInfo> searchEmailCode(Page page, EmailCodeVo emailCodeVo);

    void add(EmailCodeVo emailCodeVo);

    R sendEmailCode(EmailCodeVo emailCodeVo);

    List<EmailCodeInfo> findBy(EmailCodeVo emailCodeVo);
}
