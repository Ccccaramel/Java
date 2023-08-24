package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.BlogFile;
import com.ding.hyld.info.BlogFileInfo;
import com.ding.hyld.mapper.BlogFileMapper;
import com.ding.hyld.service.BlogFileService;
import com.ding.hyld.vo.BlogFileVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogFileServiceImpl extends ServiceImpl<BlogFileMapper, BlogFile> implements BlogFileService {
    @Override
    public void add(BlogFileVo blogFileVo) {
        baseMapper.add(blogFileVo);
    }

    @Override
    public List<BlogFileInfo> searchBlogFile(BlogFileVo blogFileVo) {
        return baseMapper.searchBlogFile(blogFileVo);
    }

}
