package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Gear;
import com.ding.hyld.info.GearInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.GearVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GearMapper extends BaseMapper<Gear> {
    List<GearInfo> searchGear(@Param("page") Page page, @Param("gearVo") GearVo gearVo);

    void update(@Param("gearVo") GearVo gearVo);

    void add(@Param("gearVo") GearVo gearVo);

    Integer searchGearOfPage(@Param("gearVo") GearVo gearVo);
}
