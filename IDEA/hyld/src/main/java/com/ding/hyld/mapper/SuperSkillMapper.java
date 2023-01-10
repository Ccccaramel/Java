package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.NormalAttack;
import com.ding.hyld.entity.SuperSkill;
import com.ding.hyld.info.NormalAttackInfo;
import com.ding.hyld.info.SuperSkillInfo;
import com.ding.hyld.vo.NormalAttackVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SuperSkillVo;
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
