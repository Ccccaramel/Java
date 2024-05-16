package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.NormalAttackAttribute;
import com.ding.office.info.NormalAttackAttributeInfo;
import com.ding.office.vo.NormalAttackAttributeVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NormalAttackAttributeMapper extends BaseMapper<NormalAttackAttribute> {
    List<NormalAttackAttributeInfo> searchNormalAttackAttribute(@Param("page") Page page, @Param("normalAttackAttributeVo") NormalAttackAttributeVo normalAttackAttributeVo);

    void update(@Param("normalAttackAttributeVo") NormalAttackAttributeVo normalAttackAttributeVo);

    void add(@Param("normalAttackAttributeVo") NormalAttackAttributeVo normalAttackAttributeVo);

    void delete(@Param("normalAttackAttributeVo") NormalAttackAttributeVo normalAttackAttributeVo);

    List<NormalAttackAttributeInfo> findPithyInfoByNormalAttackId(Integer normalAttackId);

    Integer searchNormalAttackAttributeOfPage(@Param("normalAttackAttributeVo") NormalAttackAttributeVo normalAttackAttributeVo);
}
