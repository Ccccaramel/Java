package com.ding.hyld.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Pointer;
import com.ding.hyld.info.PointerInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.PointerVo;

import java.util.List;

public interface PointerService extends IService<Pointer> {
    List<PointerInfo> searchPointer(Page page, PointerVo pointerVo);

    void update(PointerVo pointerVo);

    void add(PointerVo pointerVo);

    void reply(PointerVo pointerVo);

    void updateStatus(PointerVo pointerVo);
}
