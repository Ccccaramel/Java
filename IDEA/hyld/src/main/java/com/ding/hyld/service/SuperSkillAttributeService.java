package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.NormalAttackAttribute;
import com.ding.hyld.entity.SuperSkillAttribute;
import com.ding.hyld.info.NormalAttackAttributeInfo;
import com.ding.hyld.info.SuperSkillAttributeInfo;
import com.ding.hyld.vo.NormalAttackAttributeVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SuperSkillAttributeVo;

import java.util.List;

public interface SuperSkillAttributeService extends IService<SuperSkillAttribute> {
    List<SuperSkillAttributeInfo> searchSuperSkillAttribute(Page page, SuperSkillAttributeVo superSkillAttributeVo);

    void update(SuperSkillAttributeVo superSkillAttributeVo);

    void add(SuperSkillAttributeVo superSkillAttributeVo);

    void delete(SuperSkillAttributeVo normalAttackAttributeVo);
}
