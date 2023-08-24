package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.BlogFile;
import com.ding.hyld.info.BlogFileInfo;
import com.ding.hyld.vo.BlogFileVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogFileMapper extends BaseMapper<BlogFile> {
    void add(@Param("blogFileVo") BlogFileVo blogFileVo);

    List<BlogFileInfo> searchBlogFile(@Param("blogFileVo") BlogFileVo blogFileVo);
}
