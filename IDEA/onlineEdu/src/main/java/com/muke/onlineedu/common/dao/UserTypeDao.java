package com.muke.onlineedu.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.common.entity.UserType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTypeDao extends BaseMapper<UserType> {
    UserType findBy(int typeValue);
}
