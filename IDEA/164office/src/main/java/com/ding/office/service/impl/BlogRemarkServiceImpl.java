package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.constant.DictionaryCode;
import com.ding.office.entity.BlogRemark;
import com.ding.office.info.BlogRemarkInfo;
import com.ding.office.mapper.BlogRemarkMapper;
import com.ding.office.service.BlogRemarkService;
import com.ding.office.vo.BlogRemarkVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

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
