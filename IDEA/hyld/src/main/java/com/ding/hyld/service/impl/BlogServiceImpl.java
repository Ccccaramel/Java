package com.ding.hyld.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Blog;
import com.ding.hyld.info.BlogInfo;
import com.ding.hyld.mapper.BlogMapper;
import com.ding.hyld.service.BaseService;
import com.ding.hyld.service.BlogService;
import com.ding.hyld.vo.BlogVo;
import com.ding.hyld.vo.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService, BaseService {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void init(BlogVo blogVo) {
        delay(blogVo.getUser());
        baseMapper.init(blogVo);
    }

    @Override
    public void update(BlogVo blogVo) {
        baseMapper.update(blogVo);
    }

    @Override
    public Integer searchBlogOfPage(BlogVo blogVo) {
        return baseMapper.searchBlogOfPage(blogVo);
    }

    @Override
    public void delete(BlogVo blogVo) {
        baseMapper.delete(blogVo);
    }

    @Override
    public void viewBlog(Integer id) {
        baseMapper.viewBlog(id);
    }

    @Override
    public void modify(BlogVo blogVo) {
        delay(blogVo.getUser());
        baseMapper.modify(blogVo);
    }

    @Override
    public List<BlogInfo> searchBlog(Page page, BlogVo blogVo) {
        return baseMapper.searchBlog(page,blogVo);
    }

    @Override
    public void delay(Integer userId) {
        redisTemplate.expire("login_"+userId,1,TimeUnit.DAYS);
    }
}
