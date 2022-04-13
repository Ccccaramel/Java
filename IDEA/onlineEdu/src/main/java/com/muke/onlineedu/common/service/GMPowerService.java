package com.muke.onlineedu.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.common.entity.GMPower;

import java.util.List;

public interface GMPowerService extends IService<GMPower> {
    String findByPowerNumber(int powerNumber);
    List<GMPower>  getGMPowerMessage(int powerNumber);
}
