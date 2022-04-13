package com.muke.onlineedu.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.common.entity.Evaluate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvaluateDao extends BaseMapper<Evaluate> {
    Evaluate findBy(int typeValue);
}
