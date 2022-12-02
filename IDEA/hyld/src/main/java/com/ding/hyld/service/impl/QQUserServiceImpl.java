package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.QQUser;
import com.ding.hyld.mapper.QQUserMapper;
import com.ding.hyld.service.QQUserService;
import com.ding.hyld.vo.QQUserVo;
import org.springframework.stereotype.Service;


@Service
public class QQUserServiceImpl extends ServiceImpl<QQUserMapper, QQUser> implements QQUserService {

    @Override
    public void qqUserInfoRecord(QQUserVo qqUserVo) {
        baseMapper.qqUserInfoRecord(qqUserVo);
    }
}
