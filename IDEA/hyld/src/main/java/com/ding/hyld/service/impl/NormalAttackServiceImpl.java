package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.NormalAttack;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.info.NormalAttackInfo;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.mapper.NormalAttackMapper;
import com.ding.hyld.mapper.StarPowerMapper;
import com.ding.hyld.service.NormalAttackService;
import com.ding.hyld.service.StarPowerService;
import com.ding.hyld.vo.NormalAttackVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
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
