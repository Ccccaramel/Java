package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.SuperBag;
import com.ding.office.info.SuperBagInfo;
import com.ding.office.mapper.SuperBagMapper;
import com.ding.office.service.SuperBagService;
import com.ding.office.vo.SuperBagVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperBagServiceImpl extends ServiceImpl<SuperBagMapper, SuperBag> implements SuperBagService {
    @Override
    public void add(SuperBagVo superBagVo) {
        baseMapper.add(superBagVo);
    }

    @Override
    public List<SuperBagInfo> searchSuperBag(SuperBagVo superBagVo, Page page) {
        return baseMapper.searchSuperBag(superBagVo,page);
    }

    @Override
    public Integer searchSuperBagOfPage(SuperBagVo superBagVo) {
        return baseMapper.searchSuperBagOfPage(superBagVo);
    }

    @Override
    public void update(SuperBagVo superBagVo) {
        baseMapper.update(superBagVo);
    }

    @Override
    public void delete(SuperBagVo superBagVo) {
        baseMapper.delete(superBagVo);
    }

}
