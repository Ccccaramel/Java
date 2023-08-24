package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.Blog;
import com.ding.hyld.entity.BlogRemark;
import com.ding.hyld.info.BlogInfo;
import com.ding.hyld.info.BlogRemarkInfo;
import com.ding.hyld.mapper.BlogMapper;
import com.ding.hyld.mapper.BlogRemarkMapper;
import com.ding.hyld.service.BlogRemarkService;
import com.ding.hyld.service.BlogService;
import com.ding.hyld.utils.TreeUtils;
import com.ding.hyld.vo.BlogRemarkVo;
import com.ding.hyld.vo.BlogVo;
import com.ding.hyld.vo.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogRemarkServiceImpl extends ServiceImpl<BlogRemarkMapper, BlogRemark> implements BlogRemarkService {

    @Override
    public void save(BlogRemarkVo blogRemarkVo) {
        baseMapper.save(blogRemarkVo);
    }

    @Override
    public Integer searchBlogRemarkOfPage(BlogRemarkVo blogRemarkVo) {
        return baseMapper.searchBlogRemarkOfPage(blogRemarkVo);
    }

    @Override
    public void delete(BlogRemarkVo blogRemarkVo) {
        baseMapper.delete(blogRemarkVo);
    }

    @Override
    public List<BlogRemarkInfo> searchBlogRemark(Page page, BlogRemarkVo blogRemarkVo) {
        List<BlogRemarkInfo> res = baseMapper.searchBlogRemark(page,blogRemarkVo);
        BlogRemarkVo vo = new BlogRemarkVo();
        vo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        for ( BlogRemarkInfo blogRemarkInfo : res) {
            vo.setRootId(blogRemarkInfo.getId());
            blogRemarkInfo.setChild(baseMapper.searchBlogRemark(null,vo));
        }
        return res;
    }

}
