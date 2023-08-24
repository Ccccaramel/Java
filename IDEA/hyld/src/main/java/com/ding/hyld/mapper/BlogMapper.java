package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Blog;
import com.ding.hyld.info.BlogInfo;
import com.ding.hyld.vo.BlogVo;
import com.ding.hyld.vo.Page;
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
