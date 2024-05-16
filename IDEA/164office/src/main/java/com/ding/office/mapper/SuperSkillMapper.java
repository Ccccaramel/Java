package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.SuperSkill;
import com.ding.office.info.SuperSkillInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.SuperSkillVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SuperSkillMapper extends BaseMapper<SuperSkill> {
    List<SuperSkillInfo> searchSuperSkill(@Param("page") Page page, @Param("superSkillVo") SuperSkillVo superSkillVo);

    void update(@Param("superSkillVo") SuperSkillVo superSkillVo);

    void add(@Param("superSkillVo") SuperSkillVo superSkillVo);

    SuperSkillInfo findById(Integer id);

    void delete(@Param("superSkillVo") SuperSkillVo superSkillVo);


    SuperSkillInfo findPithyInfoByGameRoleId(Integer id);

    Integer searchSuperSkillOfPage(@Param("superSkillVo") SuperSkillVo superSkillVo);
}
