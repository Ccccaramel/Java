package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.SuperBag;
import com.ding.office.info.SuperBagInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.SuperBagVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SuperBagMapper extends BaseMapper<SuperBag> {
    void add(@Param("superBagVo") SuperBagVo superBagVo);

    List<SuperBagInfo> searchSuperBag(@Param("superBagVo") SuperBagVo superBagVo, @Param("page") Page page);

    Integer searchSuperBagOfPage(@Param("superBagVo") SuperBagVo superBagVo);
    
    void update(@Param("superBagVo") SuperBagVo superBagVo);

    void delete(@Param("superBagVo") SuperBagVo superBagVo);
}
