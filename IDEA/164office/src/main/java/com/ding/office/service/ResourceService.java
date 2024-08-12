package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Resource;
import com.ding.office.info.ResourceInfo;
import com.ding.office.vo.ResourceVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface ResourceService extends IService<Resource> {
    List<ResourceInfo> searchResource(Page page, ResourceVo resourceVo);

    void update(ResourceVo resourceVo);

    void add(ResourceVo resourceVo);

    void del(ResourceVo resourceVo);

    Integer searchResourceOfPage(ResourceVo resourceVo);
}
