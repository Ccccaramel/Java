package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.SuperSkillAttribute;
import com.ding.office.info.SuperSkillAttributeInfo;
import com.ding.office.mapper.SuperSkillAttributeMapper;
import com.ding.office.service.SuperSkillAttributeService;
import com.ding.office.vo.Page;
import com.ding.office.vo.SuperSkillAttributeVo;
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

    @Override
    public Integer searchSuperSkillAttributeOfPage(SuperSkillAttributeVo superSkillAttributeVo) {
        return baseMapper.searchSuperSkillAttributeOfPage(superSkillAttributeVo);
    }
}
