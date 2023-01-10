package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.NormalAttack;
import com.ding.hyld.entity.SuperSkill;
import com.ding.hyld.info.NormalAttackInfo;
import com.ding.hyld.info.SuperSkillInfo;
import com.ding.hyld.vo.NormalAttackVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SuperSkillVo;

import java.util.List;

public interface SuperSkillService extends IService<SuperSkill> {
    List<SuperSkillInfo> searchSuperSkill(Page page, SuperSkillVo superSkillVo);

    void update(SuperSkillVo superSkillVo);

    void add(SuperSkillVo superSkillVo);

    void delete(SuperSkillVo superSkillVo);

    Integer searchSuperSkillOfPage(SuperSkillVo superSkillVo);
}
