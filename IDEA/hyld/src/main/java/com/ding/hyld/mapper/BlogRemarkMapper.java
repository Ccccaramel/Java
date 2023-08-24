package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.BlogRemark;
import com.ding.hyld.info.BlogRemarkInfo;
import com.ding.hyld.vo.BlogRemarkVo;
import com.ding.hyld.vo.Page;
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
