package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Gadget;
import com.ding.office.info.GadgetInfo;
import com.ding.office.vo.GadgetVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface GadgetService extends IService<Gadget> {
    List<GadgetInfo> searchGadget(Page page, GadgetVo gadgetVo);

    void update(GadgetVo gadgetVo);

    void add(GadgetVo gadgetVo);

    Integer searchGadgetOfPage(GadgetVo gadgetVo);
}
