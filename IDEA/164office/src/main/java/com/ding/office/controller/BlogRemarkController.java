package com.ding.office.controller;

import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.BlogRemarkService;
import com.ding.office.service.UserService;
import com.ding.office.service.impl.QQIPServiceImpl;
import com.ding.office.utils.*;
import com.ding.office.vo.BlogRemarkVo;
import com.ding.office.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/blogRemark")
public class BlogRemarkController extends BaseController {
    @Autowired
    private BlogRemarkService blogRemarkService;

    @Autowired
    private QQIPServiceImpl ibsService;

    @Autowired
    private UserService userService;

    /**
     * 保存 blogRemark 文本,纯文本
     * @param blogRemarkVo
     * @return
     */
    @PostMapping("/saveBlogRemark")
    public R saveBlogRemark(@RequestBody BlogRemarkVo blogRemarkVo, HttpServletRequest request) throws Exception {
        if(isLogin()){
            String ips = IpUtils.getIpAddress(request);
//            String ips = "36.26.68.192"; // 测试 中国内蒙古自治区呼和浩特市土默特左旗
            blogRemarkVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
            blogRemarkVo.setUser(getUserId());
            blogRemarkVo.setIp(ips);

            blogRemarkVo.setAddress(ibsService.getAddress(ips).get("address"));

            blogRemarkService.save(blogRemarkVo);
            userService.addEx(getUserId(),1);
            return R.success("发布成功!");
        }else{
            return R.fail("请先登录!");
        }

    }

    @GetMapping("/searchBlogRemark")
    public R searchBlogRemark(Page page, BlogRemarkVo blogRemarkVo){
        HashMap<String,Object> result=new HashMap<>();
        blogRemarkVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        blogRemarkVo.setParentId(-1);
        result.put("data",blogRemarkService.searchBlogRemark(page, blogRemarkVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(blogRemarkService.searchBlogRemarkOfPage(blogRemarkVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }
}