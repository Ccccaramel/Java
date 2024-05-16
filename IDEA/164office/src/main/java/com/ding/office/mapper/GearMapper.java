package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Gear;
import com.ding.office.info.GearInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.GearVo;
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
