package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.NormalAttackAttribute;
import com.ding.hyld.info.NormalAttackAttributeInfo;
import com.ding.hyld.mapper.NormalAttackAttributeMapper;
import com.ding.hyld.service.NormalAttackAttributeService;
import com.ding.hyld.vo.NormalAttackAttributeVo;
import com.ding.hyld.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NormalAttackAttributeServiceImpl extends ServiceImpl<NormalAttackAttributeMapper, NormalAttackAttribute> implements NormalAttackAttributeService {
    @Override
    public List<NormalAttackAttributeInfo> searchNormalAttackAttribute(Page page, NormalAttackAttributeVo normalAttackAttributeVo) {
        return baseMapper.searchNormalAttackAttribute(page, normalAttackAttributeVo);
    }

    @Override
    public void update(NormalAttackAttributeVo normalAttackAttributeVo) {
        baseMapper.update(normalAttackAttributeVo);
    }

    @Override
    public void add(NormalAttackAttributeVo normalAttackAttributeVo) {
        baseMapper.add(normalAttackAttributeVo);
    }

    @Override
    public void delete(NormalAttackAttributeVo normalAttackAttributeVo) {
        baseMapper.delete(normalAttackAttributeVo);
    }

    @Override
    public Integer searchNormalAttackAttributeOfPage(NormalAttackAttributeVo normalAttackAttributeVo) {
        return baseMapper.searchNormalAttackAttributeOfPage(normalAttackAttributeVo);
    }
}
