package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Gadget;
import com.ding.hyld.info.GadgetInfo;
import com.ding.hyld.vo.GadgetVo;
import com.ding.hyld.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GadgetMapper extends BaseMapper<Gadget> {
    List<GadgetInfo> searchGadget(@Param("page") Page page, @Param("gadgetVo") GadgetVo gadgetVo);

    void update(@Param("gadgetVo") GadgetVo gadgetVo);

    void add(@Param("gadgetVo") GadgetVo gadgetVo);

    List<GadgetInfo> findPithyInfoByGameRoleId(Integer id);

    Integer searchGadgetOfPage(@Param("gadgetVo") GadgetVo gadgetVo);
}
