package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.SuperBag;
import com.ding.office.info.SuperBagInfo;
import com.ding.office.vo.SuperBagVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface SuperBagService extends IService<SuperBag> {
    void add(SuperBagVo superBagVo);

    List<SuperBagInfo> searchSuperBag(SuperBagVo superBagVo, Page page);

    Integer searchSuperBagOfPage(SuperBagVo superBagVo);
    void update(SuperBagVo superBagVo);
    void delete(SuperBagVo superBagVo);
}
