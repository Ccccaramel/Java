package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.QQUser;
import com.ding.office.vo.QQUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QQUserMapper extends BaseMapper<QQUser> {
    void qqUserInfoRecord(@Param("qqUserVo") QQUserVo qqUserVo);
}
