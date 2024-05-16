package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.SuperSkill;
import com.ding.office.info.SuperSkillInfo;
import com.ding.office.mapper.SuperSkillMapper;
import com.ding.office.service.SuperSkillService;
import com.ding.office.vo.Page;
import com.ding.office.vo.SuperSkillVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperSkillServiceImpl extends ServiceImpl<SuperSkillMapper, SuperSkill> implements SuperSkillService {
    @Override
    public List<SuperSkillInfo> searchSuperSkill(Page page, SuperSkillVo superSkillVo) {
        return baseMapper.searchSuperSkill(page, superSkillVo);
    }

    @Override
    public void update(SuperSkillVo superSkillVo) {
        baseMapper.update(superSkillVo);
    }

    @Override
    public void add(SuperSkillVo superSkillVo) {
        baseMapper.add(superSkillVo);
    }

    @Override
    public void delete(SuperSkillVo superSkillVo) {
        baseMapper.delete(superSkillVo);
    }

    @Override
    public Integer searchSuperSkillOfPage(SuperSkillVo superSkillVo) {
        return baseMapper.searchSuperSkillOfPage(superSkillVo);
    }
}
