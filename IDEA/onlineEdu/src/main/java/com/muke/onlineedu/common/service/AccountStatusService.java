package com.muke.onlineedu.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.common.entity.AccountStatus;

public interface AccountStatusService extends IService<AccountStatus> {
    String findBy(int accountNumber);
}
