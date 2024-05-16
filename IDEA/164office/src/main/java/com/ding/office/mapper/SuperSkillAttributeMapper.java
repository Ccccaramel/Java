package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.SuperSkillAttribute;
import com.ding.office.info.SuperSkillAttributeInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.SuperSkillAttributeVo;
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

    Integer searchSuperSkillAttributeOfPage(@Param("superSkillAttributeVo") SuperSkillAttributeVo superSkillAttributeVo);
}
