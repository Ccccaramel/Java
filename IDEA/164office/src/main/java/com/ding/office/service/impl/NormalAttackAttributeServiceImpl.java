package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.NormalAttackAttribute;
import com.ding.office.info.NormalAttackAttributeInfo;
import com.ding.office.mapper.NormalAttackAttributeMapper;
import com.ding.office.service.NormalAttackAttributeService;
import com.ding.office.vo.NormalAttackAttributeVo;
import com.ding.office.vo.Page;
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
