package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.BlogFile;
import com.ding.office.info.BlogFileInfo;
import com.ding.office.mapper.BlogFileMapper;
import com.ding.office.service.BlogFileService;
import com.ding.office.vo.BlogFileVo;
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
