package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.StarPower;
import com.ding.office.info.StarPowerInfo;
import com.ding.office.mapper.StarPowerMapper;
import com.ding.office.service.StarPowerService;
import com.ding.office.vo.Page;
import com.ding.office.vo.StarPowerVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarPowerServiceImpl extends ServiceImpl<StarPowerMapper, StarPower> implements StarPowerService {
    @Override
    public List<StarPowerInfo> searchStarPower(Page page, StarPowerVo starPowerVo) {
        return baseMapper.searchStarPower(page, starPowerVo);
    }

    @Override
    public void update(StarPowerVo starPowerVo) {
        baseMapper.update(starPowerVo);
    }

    @Override
    public void add(StarPowerVo starPowerVo) {
        baseMapper.add(starPowerVo);
    }

    @Override
    public Integer searchStarPowerOfPage(StarPowerVo starPowerVo) {
        return baseMapper.searchStarPowerOfPage(starPowerVo);
    }
}
