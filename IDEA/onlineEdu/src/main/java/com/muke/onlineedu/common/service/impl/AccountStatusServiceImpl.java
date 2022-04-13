package com.muke.onlineedu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.common.dao.AccountStatusDao;
import com.muke.onlineedu.common.entity.AccountStatus;
import com.muke.onlineedu.common.service.AccountStatusService;
import org.springframework.stereotype.Service;

@Service("accountStatusServiceImpl")
public class AccountStatusServiceImpl extends ServiceImpl<AccountStatusDao, AccountStatus> implements AccountStatusService {
    @Override
    public String findBy(int accountNumber){
        return baseMapper.findBy(accountNumber).getAccountClass();
    }
}
