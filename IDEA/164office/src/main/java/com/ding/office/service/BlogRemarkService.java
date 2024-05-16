package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.BlogRemark;
import com.ding.office.info.BlogRemarkInfo;
import com.ding.office.vo.BlogRemarkVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface BlogRemarkService extends IService<BlogRemark> {
    void save(BlogRemarkVo blogRemarkVo);

    List<BlogRemarkInfo> searchBlogRemark(Page page, BlogRemarkVo blogRemarkVo);

    Integer searchBlogRemarkOfPage(BlogRemarkVo blogRemarkVo);

    void delete(BlogRemarkVo blogRemarkVo);
}
