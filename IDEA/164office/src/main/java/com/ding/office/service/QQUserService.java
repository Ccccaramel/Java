package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.QQUser;
import com.ding.office.vo.QQUserVo;

public interface QQUserService extends IService<QQUser> {
    void qqUserInfoRecord(QQUserVo qqUserVo);
}
