package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.NormalAttack;
import com.ding.office.info.NormalAttackInfo;
import com.ding.office.mapper.NormalAttackMapper;
import com.ding.office.service.NormalAttackService;
import com.ding.office.vo.NormalAttackVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NormalAttackServiceImpl extends ServiceImpl<NormalAttackMapper, NormalAttack> implements NormalAttackService {
    @Override
    public List<NormalAttackInfo> searchNormalAttack(Page page, NormalAttackVo normalAttackVo) {
        return baseMapper.searchNormalAttack(page, normalAttackVo);
    }

    @Override
    public void update(NormalAttackVo normalAttackVo) {
        baseMapper.update(normalAttackVo);
    }

    @Override
    public void add(NormalAttackVo normalAttackVo) {
        baseMapper.add(normalAttackVo);
    }

    @Override
    public void delete(NormalAttackVo normalAttackVo) {
        baseMapper.delete(normalAttackVo);
    }

    @Override
    public Integer searchNormalAttackOfPage(NormalAttackVo normalAttackVo) {
        return baseMapper.searchNormalAttackOfPage(normalAttackVo);
    }
}
