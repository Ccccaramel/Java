package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.BlogCollection;
import com.ding.office.info.BlogCollectionInfo;
import com.ding.office.vo.BlogCollectionVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogCollectionService extends IService<BlogCollection> {
    void collection(BlogCollectionVo blogCollectionVo);

    void cancelCollection(BlogCollectionVo blogCollectionVo);

    List<BlogCollectionInfo> searchBlogCollection(BlogCollectionVo blogCollectionVo, Page page);
}
