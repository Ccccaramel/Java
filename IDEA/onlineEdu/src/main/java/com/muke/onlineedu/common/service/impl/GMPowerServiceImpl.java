package com.muke.onlineedu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.common.dao.GMPowerDao;
import com.muke.onlineedu.common.entity.GMPower;
import com.muke.onlineedu.common.service.GMPowerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("gmPowerService")
public class GMPowerServiceImpl extends ServiceImpl<GMPowerDao, GMPower> implements GMPowerService {

    @Override
    public String findByPowerNumber(int powerNumber) {
        return baseMapper.findByPowerNumber(powerNumber);
    }

    @Override
    public List<GMPower> getGMPowerMessage(int powerNumber){
        return baseMapper.getGMPowerMessage(powerNumber);
    }
}
