package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.Resource;
import com.ding.office.info.ResourceInfo;
import com.ding.office.mapper.ResourceMapper;
import com.ding.office.service.ResourceService;
import com.ding.office.vo.ResourceVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    @Override
    public List<ResourceInfo> searchResource(Page page, ResourceVo resourceVo) {
        return baseMapper.searchResource(page, resourceVo);
    }

    @Override
    public void update(ResourceVo resourceVo) {
        baseMapper.update(resourceVo);
    }

    @Override
    public void add(ResourceVo resourceVo) {
        baseMapper.add(resourceVo);
    }

    @Override
    public void del(ResourceVo resourceVo) {
        baseMapper.del(resourceVo);
    }

    @Override
    public Integer searchResourceOfPage(ResourceVo resourceVo) {
        return baseMapper.searchResourceOfPage(resourceVo);
    }
}
