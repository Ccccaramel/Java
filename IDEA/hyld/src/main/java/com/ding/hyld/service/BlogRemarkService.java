package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Blog;
import com.ding.hyld.entity.BlogRemark;
import com.ding.hyld.info.BlogInfo;
import com.ding.hyld.info.BlogRemarkInfo;
import com.ding.hyld.vo.BlogRemarkVo;
import com.ding.hyld.vo.BlogVo;
import com.ding.hyld.vo.Page;

import java.util.List;

public interface BlogRemarkService extends IService<BlogRemark> {
    void save(BlogRemarkVo blogRemarkVo);

    List<BlogRemarkInfo> searchBlogRemark(Page page, BlogRemarkVo blogRemarkVo);

    Integer searchBlogRemarkOfPage(BlogRemarkVo blogRemarkVo);

    void delete(BlogRemarkVo blogRemarkVo);
}
