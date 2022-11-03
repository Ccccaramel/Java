package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Pointer;
import com.ding.hyld.info.PointerInfo;
import com.ding.hyld.mapper.PointerMapper;
import com.ding.hyld.service.PointerService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.PointerVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointerServiceImpl extends ServiceImpl<PointerMapper, Pointer> implements PointerService {
    @Override
    public List<PointerInfo> searchPointer(Page page, PointerVo pointerVo) {
        return baseMapper.searchPointer(page,pointerVo);
    }

    @Override
    public void update(PointerVo pointerVo) {
        baseMapper.update(pointerVo);
    }

    @Override
    public void add(PointerVo pointerVo) {
        baseMapper.add(pointerVo);
    }

    @Override
    public void reply(PointerVo pointerVo) {
        baseMapper.reply(pointerVo);
    }

    @Override
    public void updateStatus(PointerVo pointerVo) {
        baseMapper.updateStatus(pointerVo);
    }
}
