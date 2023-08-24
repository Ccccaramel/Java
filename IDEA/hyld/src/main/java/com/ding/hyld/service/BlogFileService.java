package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.BlogFile;
import com.ding.hyld.info.BlogFileInfo;
import com.ding.hyld.vo.BlogFileVo;

import java.util.List;

public interface BlogFileService extends IService<BlogFile> {
    void add(BlogFileVo blogFileVo);

    List<BlogFileInfo> searchBlogFile(BlogFileVo blogFileVo);
}
