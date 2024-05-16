package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.SuperSkillAttribute;
import com.ding.office.info.SuperSkillAttributeInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.SuperSkillAttributeVo;

import java.util.List;

public interface SuperSkillAttributeService extends IService<SuperSkillAttribute> {
    List<SuperSkillAttributeInfo> searchSuperSkillAttribute(Page page, SuperSkillAttributeVo superSkillAttributeVo);

    void update(SuperSkillAttributeVo superSkillAttributeVo);

    void add(SuperSkillAttributeVo superSkillAttributeVo);

    void delete(SuperSkillAttributeVo normalAttackAttributeVo);

    Integer searchSuperSkillAttributeOfPage(SuperSkillAttributeVo superSkillAttributeVo);
}
