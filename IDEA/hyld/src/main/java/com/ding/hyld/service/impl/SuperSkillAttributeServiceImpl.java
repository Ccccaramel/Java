package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.NormalAttackAttribute;
import com.ding.hyld.entity.SuperSkillAttribute;
import com.ding.hyld.info.NormalAttackAttributeInfo;
import com.ding.hyld.info.SuperSkillAttributeInfo;
import com.ding.hyld.mapper.NormalAttackAttributeMapper;
import com.ding.hyld.mapper.SuperSkillAttributeMapper;
import com.ding.hyld.service.NormalAttackAttributeService;
import com.ding.hyld.service.SuperSkillAttributeService;
import com.ding.hyld.vo.NormalAttackAttributeVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SuperSkillAttributeVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperSkillAttributeServiceImpl extends ServiceImpl<SuperSkillAttributeMapper, SuperSkillAttribute> implements SuperSkillAttributeService {
    @Override
    public List<SuperSkillAttributeInfo> searchSuperSkillAttribute(Page page, SuperSkillAttributeVo superSkillAttributeVo) {
        return baseMapper.searchSuperSkillAttribute(page, superSkillAttributeVo);
    }

    @Override
    public void update(SuperSkillAttributeVo superSkillAttributeVo) {
        baseMapper.update(superSkillAttributeVo);
    }

    @Override
    public void add(SuperSkillAttributeVo superSkillAttributeVo) {
        baseMapper.add(superSkillAttributeVo);
    }

    @Override
    public void delete(SuperSkillAttributeVo superSkillAttributeVo) {
        baseMapper.delete(superSkillAttributeVo);
    }
}
