package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.BlogCollection;
import com.ding.office.info.BlogCollectionInfo;
import com.ding.office.mapper.BlogCollectionMapper;
import com.ding.office.service.BlogCollectionService;
import com.ding.office.vo.BlogCollectionVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCollectionServiceImpl extends ServiceImpl<BlogCollectionMapper, BlogCollection> implements BlogCollectionService {
    @Override
    public void collection(BlogCollectionVo blogCollectionVo) {
        baseMapper.collection(blogCollectionVo);
    }

    @Override
    public void cancelCollection(BlogCollectionVo blogCollectionVo) {
        baseMapper.cancelCollection(blogCollectionVo);
    }

    @Override
    public List<BlogCollectionInfo> searchBlogCollection(BlogCollectionVo blogCollectionVo, Page page) {
        return baseMapper.searchBlogCollection(blogCollectionVo, page);
    }
}
