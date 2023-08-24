package com.ding.hyld.controller;

import com.alibaba.fastjson.JSONObject;
import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.info.BlogRemarkInfo;
import com.ding.hyld.info.ibs.AddressInfo;
import com.ding.hyld.info.ibs.IpInfo;
import com.ding.hyld.service.BlogFileService;
import com.ding.hyld.service.BlogRemarkService;
import com.ding.hyld.service.IBSService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.*;
import com.ding.hyld.vo.BlogFileVo;
import com.ding.hyld.vo.BlogRemarkVo;
import com.ding.hyld.vo.BlogVo;
import com.ding.hyld.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/blogRemark")
public class BlogRemarkController extends BaseController {
    @Autowired
    private BlogRemarkService blogRemarkService;

    @Autowired
    private IBSService ibsService;

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
            try{
                IpInfo ipInfo = JSONObject.toJavaObject(ibsService.ip(CommonCode.IBS_KEY,ips), IpInfo.class);
                AddressInfo addressInfo = ipInfo.getResult().getAd_info();
                blogRemarkVo.setIp(ips);
                blogRemarkVo.setAddress(addressInfo.getNation()+addressInfo.getProvince());

            }catch (Exception e){
//                throw new Exception("IBS请求异常");
                blogRemarkVo.setAddress("M78星云");
                System.out.println("IBS请求异常");
            }

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