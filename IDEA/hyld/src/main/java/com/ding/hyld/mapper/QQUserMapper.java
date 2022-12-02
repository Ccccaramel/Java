package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.QQUser;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.UserInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.QQUserVo;
import com.ding.hyld.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QQUserMapper extends BaseMapper<QQUser> {
    void qqUserInfoRecord(@Param("qqUserVo") QQUserVo qqUserVo);
}
