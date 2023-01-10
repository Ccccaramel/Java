package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.NormalAttack;
import com.ding.hyld.entity.NormalAttackAttribute;
import com.ding.hyld.info.NormalAttackAttributeInfo;
import com.ding.hyld.info.NormalAttackInfo;
import com.ding.hyld.vo.NormalAttackAttributeVo;
import com.ding.hyld.vo.NormalAttackVo;
import com.ding.hyld.vo.Page;
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
