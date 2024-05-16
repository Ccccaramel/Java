package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.BlogFile;
import com.ding.office.info.BlogFileInfo;
import com.ding.office.vo.BlogFileVo;

import java.util.List;

public interface BlogFileService extends IService<BlogFile> {
    void add(BlogFileVo blogFileVo);

    List<BlogFileInfo> searchBlogFile(BlogFileVo blogFileVo);
}
