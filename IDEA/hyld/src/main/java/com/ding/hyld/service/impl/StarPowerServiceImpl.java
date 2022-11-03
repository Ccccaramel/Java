package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.mapper.StarPowerMapper;
import com.ding.hyld.mapper.UpdateLogMapper;
import com.ding.hyld.service.StarPowerService;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
import com.ding.hyld.vo.UpdateLogVo;
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
}
