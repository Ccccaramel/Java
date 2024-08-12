package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.BlogCollection;
import com.ding.office.info.BlogCollectionInfo;
import com.ding.office.vo.BlogCollectionVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogCollectionMapper extends BaseMapper<BlogCollection> {
    void cancelCollection(@Param("blogCollectionVo") BlogCollectionVo blogCollectionVo);

    void collection(@Param("blogCollectionVo") BlogCollectionVo blogCollectionVo);

    List<BlogCollectionInfo> searchBlogCollection(@Param("blogCollectionVo") BlogCollectionVo blogCollectionVo, @Param("page") Page page);
}
