package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.BlogRemark;
import com.ding.office.info.BlogRemarkInfo;
import com.ding.office.vo.BlogRemarkVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogRemarkMapper extends BaseMapper<BlogRemark> {
    void save(@Param("blogRemarkVo") BlogRemarkVo blogRemarkVo);

    List<BlogRemarkInfo> searchBlogRemark(@Param("page") Page page, @Param("blogRemarkVo") BlogRemarkVo blogRemarkVo);

    Integer searchBlogRemarkOfPage(@Param("blogRemarkVo") BlogRemarkVo blogRemarkVo);

    void delete(@Param("blogRemarkVo") BlogRemarkVo blogRemarkVo);
}
