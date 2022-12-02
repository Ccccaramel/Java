package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.QQUser;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.UserInfo;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.QQUserVo;
import com.ding.hyld.vo.UserVo;

import java.util.List;

public interface QQUserService extends IService<QQUser> {
    void qqUserInfoRecord(QQUserVo qqUserVo);
}
