package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Resource;
import com.ding.office.info.ResourceInfo;
import com.ding.office.vo.ResourceVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
    List<ResourceInfo> searchResource(@Param("page") Page page, @Param("resourceVo") ResourceVo resourceVo);

    void update(@Param("resourceVo") ResourceVo resourceVo);

    void add(@Param("resourceVo") ResourceVo resourceVo);

    ResourceInfo findById(Integer id);

    void del(@Param("resourceVo") ResourceVo resourceVo);

    Integer searchResourceOfPage(@Param("resourceVo") ResourceVo resourceVo);
}
