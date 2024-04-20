package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.ChatService;
import com.ding.hyld.service.HeadPortraitService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.ChatVo;
import com.ding.hyld.vo.HeadPortraitVo;
import com.ding.hyld.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/chat")
public class ChatController extends BaseController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/searchAllChat")
    public R searchAllChat(Page page, ChatVo chatVo){
        HashMap<String,Object> result=new HashMap<>();

        chatVo.setUser(getUserId());
        result.put("data",chatService.searchChat(page, chatVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(chatService.searchChatOfPage(chatVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/sendMessage")
    public R sendMessage(@RequestBody ChatVo chatVo){
        chatVo.setSender(getUserId());
        chatVo.setStatus(DictionaryCode.CHAT_STATUS_1);
        if(chatVo.getRecipient()==null|| chatVo.getRecipient()==0){
            chatVo.setRecipient(getUserId());  // 由于数据库设置了外键,所以该属性不可为空,那就设置成自己吧
            chatVo.setType(DictionaryCode.CHAT_TYPE_2);
        }
        else{
            chatVo.setType(DictionaryCode.CHAT_TYPE_1);
        }
        chatService.add(chatVo);
        return R.success("消息发送成功!");
    }

    /**
     * 获取指定用户指定页数的对话数据
     * @param chatVo
     * @return
     */
    @GetMapping("/searchChat")  // 放开,务必严格查询条件
    public R searchChat(Page page, ChatVo chatVo){
        HashMap<String,Object> result=new HashMap<>();

        if(isLogin()){
            chatVo.setUser(getUserId());
        }
        else{
            chatVo.setHall(true);
        }
        if(!isLogin()||chatVo.getRecipient()==null){
            chatVo.setType(DictionaryCode.CHAT_TYPE_2);
        }
        else{
            chatVo.setType(DictionaryCode.CHAT_TYPE_1);
        }
        result.put("data",chatService.searchChat(page,chatVo));
        return R.success(result);
    }

}