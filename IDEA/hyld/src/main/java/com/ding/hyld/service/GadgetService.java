package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Gadget;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.info.GadgetInfo;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.vo.GadgetVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;

import java.util.List;

public interface GadgetService extends IService<Gadget> {
    List<GadgetInfo> searchGadget(Page page, GadgetVo gadgetVo);

    void update(GadgetVo gadgetVo);

    void add(GadgetVo gadgetVo);
}
