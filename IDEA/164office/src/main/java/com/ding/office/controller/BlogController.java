package com.ding.office.controller;

import com.ding.office.constant.CommonCode;
import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.info.BlogInfo;
import com.ding.office.service.BlogService;
import com.ding.office.service.UserService;
import com.ding.office.service.impl.QQIPServiceImpl;
import com.ding.office.utils.IpUtils;
import com.ding.office.utils.R;
import com.ding.office.vo.BlogVo;
import com.ding.office.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/blog")
public class BlogController extends BaseController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private QQIPServiceImpl ibsService;

    @PostMapping("/initBlog")
    public R initBlog(BlogVo blogVo, HttpServletRequest request){
        HashMap<String,Object> result=new HashMap<>();

        blogVo.setUser(getUserId());
        blogVo.setTitle(CommonCode.STRING_1);
        blogVo.setContent(CommonCode.STRING_2);
        blogVo.setStatus(DictionaryCode.BLOG_STATUS_2);
        blogVo.setPower(DictionaryCode.BLOG_POWER_1);
        blogVo.setType(DictionaryCode.BLOG_TYPE_1);

        String ips = IpUtils.getIpAddress(request);
        blogVo.setIp(ips);

        blogVo.setAddress(ibsService.getAddress(ips).get("address"));

        blogService.init(blogVo);
        result.put("blogId",blogVo.getId());
//        result.put("blogId",1); // 测试使用,博客模块完成后替换成上面

        blogService.delay(getUserId());
        userService.addEx(getUserId(),5);
        return R.success(result);
    }

    /**
     * 用于 用户编辑 或 查看 博客而获取博客数据
     * @param id
     * @param isEdit
     * @return
     */
    @GetMapping("/findBlogById")
    public R findBlogById(Integer id,boolean isEdit){
        HashMap<String,Object> result=new HashMap<>();
        List<BlogInfo> infos;
        if(id==null){
            return R.fail("博客信息获取失败!");
        }else{
            BlogVo blogVo = new BlogVo();
            blogVo.setId(id);

            if(isEdit){ // 编辑
                if(isLogin()){ // 已经登录了
                    blogVo.setUser(getUserId());
                    blogVo.setStatus(DictionaryCode.BLOG_STATUS_2);
                    blogService.delay(getUserId());
                }else{  // 未登录的游客是不允许编辑的
                    return R.fail("非法访问!");
                }
            }else{ // 浏览查看
                if(isLogin()){ // 已经登录了
                    if(isRoot()){ // 管理员
                        // 无限制
                    }else{ // 普通用户需要特殊判断一次 正常|(自己的博客(公开|私有)|他人博客(公开))
                        blogVo.setStatus(DictionaryCode.BLOG_STATUS_2);
                    }
                }else{ // 未登录的游客金可查看 公开状态+正常状态
                    blogVo.setStatus(DictionaryCode.BLOG_STATUS_2);
                    blogVo.setPower(DictionaryCode.BLOG_POWER_1);
                }
            }

            infos = blogService.searchBlog(null, blogVo);
            if(infos.size()!=1){
                return R.fail("服务器内部错误!请联系管理员");
            }
            BlogInfo info =infos.get(0);

            if(!isEdit&&isLogin()&&!isRoot()){ // 浏览查看+已经登录+普通用户
                if(!getUserId().equals(info.getUserInfo().getId())&&DictionaryCode.BLOG_POWER_2.equals(info.getPower().getId())){ // 所查看的博客既不是自己的也不是公开的
                    return R.fail("非法访问!");
                }
            }

            result.put("blogInfo",info);
            return R.success(result);
        }
    }

    /**
     * 保存 blog 文本,纯文本
     * @param blogVo
     * @return
     */
//    @PreAuthorize("hasAnyAuthority('headPortraitManage_add','headPortraitManage_update')")
    @PostMapping("/saveBlog")
    public R saveBlog(@RequestBody BlogVo blogVo) {
        if(blogVo.getId()==null){
            return R.fail("非法操作!");
        }

        BlogVo vo = new BlogVo();
        vo.setId(blogVo.getId());
        vo.setUser(getUserId());
        List<BlogInfo> infos = blogService.searchBlog(null, vo);
        if(infos.get(0).getStatus().equals(DictionaryCode.BLOG_STATUS_3)){
            return R.fail("非法操作!");
        }

        // text 类型不允许为空
        if(blogVo.getContent()==null || blogVo.getContent().isEmpty()){
            blogVo.setContent(infos.get(0).getContent());
        }

        blogService.modify(blogVo);
        String msg = "更改已保存!";
        if(blogVo.isAutoSave()){
            msg = "更改已自动保存!";
        }

        return R.success(msg);
    }

    @GetMapping("/searchMyBlog")
    public R searchMyBlog(BlogVo blogVo,Page page){
        HashMap<String,Object> result=new HashMap<>();
        blogVo.setUser(getUserId());
        blogVo.setStatus(DictionaryCode.BLOG_STATUS_2);

        result.put("data",blogService.searchBlog(page, blogVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(blogService.searchBlogOfPage(blogVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @GetMapping("/searchBlog")
    public R searchBlog(BlogVo blogVo,Page page){
        HashMap<String,Object> result=new HashMap<>();
        blogVo.setStatus(DictionaryCode.BLOG_STATUS_2);
        blogVo.setPower(DictionaryCode.BLOG_POWER_1);

        result.put("data",blogService.searchBlog(page, blogVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(blogService.searchBlogOfPage(blogVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    //    @PreAuthorize("hasAuthority('headPortraitManage_delete')")
    @PostMapping("/deleteBlog")
    public R deleteBlog(@RequestBody BlogVo blogVo){
        blogVo.setStatus(DictionaryCode.BLOG_STATUS_3);
        blogService.delete(blogVo);
        return R.success("博客成功删除!");
    }

    @PostMapping("/viewBlog")
    public R viewBlog(Integer id){
        blogService.viewBlog(id);
        return R.success();
    }

}