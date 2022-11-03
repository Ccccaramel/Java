package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.NormalAttackAttribute;
import com.ding.hyld.entity.SuperSkillAttribute;
import com.ding.hyld.info.NormalAttackAttributeInfo;
import com.ding.hyld.info.SuperSkillAttributeInfo;
import com.ding.hyld.vo.NormalAttackAttributeVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SuperSkillAttributeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SuperSkillAttributeMapper extends BaseMapper<SuperSkillAttribute> {
    List<SuperSkillAttributeInfo> searchSuperSkillAttribute(@Param("page") Page page, @Param("superSkillAttributeVo") SuperSkillAttributeVo superSkillAttributeVo);

    void update(@Param("superSkillAttributeVo") SuperSkillAttributeVo superSkillAttributeVo);

    void add(@Param("superSkillAttributeVo") SuperSkillAttributeVo superSkillAttributeVo);

    void delete(@Param("superSkillAttributeVo") SuperSkillAttributeVo superSkillAttributeVo);

    List<SuperSkillAttributeInfo> findPithyInfoBySuperSkillId(Integer superSkillId);
}
