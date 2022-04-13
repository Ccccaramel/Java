package com.muke.onlineedu.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.common.entity.GMPower;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GMPowerDao extends BaseMapper<GMPower> {
    String findByPowerNumber(int powerNumber);
    List<GMPower> getGMPowerMessage(int powerNumber);
}
