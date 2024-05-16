package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Blog;
import com.ding.office.info.BlogInfo;
import com.ding.office.vo.BlogVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface BlogService extends IService<Blog>,BaseService {
    void init(BlogVo blogVo);

    List<BlogInfo> searchBlog(Page page, BlogVo blogVo);

    void update(BlogVo blogVo);

    Integer searchBlogOfPage(BlogVo blogVo);

    void delete(BlogVo blogVo);

    void viewBlog(Integer id);

    void modify(BlogVo blogVo);
}
