package com.ding.office.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Pointer;
import com.ding.office.info.PointerInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.PointerVo;

import java.util.List;

public interface PointerService extends IService<Pointer> {
    List<PointerInfo> searchPointer(Page page, PointerVo pointerVo);

    void update(PointerVo pointerVo);

    void add(PointerVo pointerVo);

    void reply(PointerVo pointerVo);

    void updateStatus(PointerVo pointerVo);

    Integer searchPointerOfPage(PointerVo pointerVo);
}
