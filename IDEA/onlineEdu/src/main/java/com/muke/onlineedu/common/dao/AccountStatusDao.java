package com.muke.onlineedu.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.common.entity.AccountStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountStatusDao extends BaseMapper<AccountStatus> {
    AccountStatus findBy(int accountNumber);
}
