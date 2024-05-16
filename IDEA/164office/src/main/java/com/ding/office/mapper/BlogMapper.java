package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Blog;
import com.ding.office.info.BlogInfo;
import com.ding.office.vo.BlogVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    void init(@Param("blogVo") BlogVo blogVo);

    void update(@Param("blogVo") BlogVo blogVo);

    void modify(@Param("blogVo") BlogVo blogVo);

    List<BlogInfo> searchBlog(@Param("page") Page page, @Param("blogVo") BlogVo blogVo);

    Integer searchBlogOfPage(@Param("blogVo") BlogVo blogVo);

    void delete(@Param("blogVo") BlogVo blogVo);

    void viewBlog(Integer id);
}
