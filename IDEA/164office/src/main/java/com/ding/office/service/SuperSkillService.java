package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.SuperSkill;
import com.ding.office.info.SuperSkillInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.SuperSkillVo;

import java.util.List;

public interface SuperSkillService extends IService<SuperSkill> {
    List<SuperSkillInfo> searchSuperSkill(Page page, SuperSkillVo superSkillVo);

    void update(SuperSkillVo superSkillVo);

    void add(SuperSkillVo superSkillVo);

    void delete(SuperSkillVo superSkillVo);

    Integer searchSuperSkillOfPage(SuperSkillVo superSkillVo);
}
