package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.NormalAttack;
import com.ding.hyld.entity.SuperSkill;
import com.ding.hyld.info.NormalAttackInfo;
import com.ding.hyld.info.SuperSkillInfo;
import com.ding.hyld.mapper.NormalAttackMapper;
import com.ding.hyld.mapper.SuperSkillMapper;
import com.ding.hyld.service.NormalAttackService;
import com.ding.hyld.service.SuperSkillService;
import com.ding.hyld.vo.NormalAttackVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SuperSkillVo;
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
