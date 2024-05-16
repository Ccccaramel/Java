package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.QQUser;
import com.ding.office.mapper.QQUserMapper;
import com.ding.office.service.QQUserService;
import com.ding.office.vo.QQUserVo;
import org.springframework.stereotype.Service;


@Service
public class QQUserServiceImpl extends ServiceImpl<QQUserMapper, QQUser> implements QQUserService {

    @Override
    public void qqUserInfoRecord(QQUserVo qqUserVo) {
        baseMapper.qqUserInfoRecord(qqUserVo);
    }
}
