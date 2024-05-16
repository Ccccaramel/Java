package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Pointer;
import com.ding.office.info.PointerInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.PointerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PointerMapper extends BaseMapper<Pointer> {
    List<PointerInfo> searchPointer(@Param("page") Page page, @Param("pointerVo") PointerVo pointerVo);

    void update(@Param("pointerVo") PointerVo pointerVo);

    void add(@Param("pointerVo") PointerVo pointerVo);

    void reply(@Param("pointerVo") PointerVo pointerVo);

    void updateStatus(@Param("pointerVo") PointerVo pointerVo);

    Integer searchPointerOfPage(@Param("pointerVo") PointerVo pointerVo);
}
