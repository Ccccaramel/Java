package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.entity.BlogCollection;
import com.ding.office.info.BlogCollectionInfo;
import com.ding.office.service.BlogCollectionService;
import com.ding.office.utils.R;
import com.ding.office.vo.BlogCollectionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/blogCollection")
public class BlogCollectionController extends BaseController {

    @Autowired
    private BlogCollectionService blogCollectionService;

    @PostMapping("/collectionBlog")
    public R collectionBlog(@RequestBody BlogCollectionVo blogCollectionVo){
        blogCollectionVo.setUser(getUserId());
        log.info("blogCollectionVo   {}",blogCollectionVo);
        if(blogCollectionVo.getCollection()){
            blogCollectionService.collection(blogCollectionVo);
            return R.success("收藏成功!");
        }
        else{
            blogCollectionService.cancelCollection(blogCollectionVo);
            return R.success("已取消收藏!");
        }
    }


    @PostMapping("/collectionStatus")
    public R collectionStatus(@RequestBody BlogCollectionVo blogCollectionVo){
        HashMap<String,Object> result=new HashMap<>();
        if(isLogin()){
            blogCollectionVo.setUser(getUserId());
            log.info("collectionStatus   blogCollectionVo:{}",blogCollectionVo);
            List<BlogCollectionInfo> list = blogCollectionService.searchBlogCollection(blogCollectionVo, null);
            if(list.size()==1){
                result.put("status",true);
            }
            else{
                result.put("status",false);
            }
        }
        else {
            result.put("status",false);
        }
        return R.success(result);
    }
}
