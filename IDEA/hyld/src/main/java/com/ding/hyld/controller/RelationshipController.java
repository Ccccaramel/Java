package com.ding.hyld.controller;

import com.alibaba.fastjson.JSONObject;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.info.RelationshipInfo;
import com.ding.hyld.service.GadgetService;
import com.ding.hyld.service.RelationshipService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.GadgetVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.RelationshipVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 随身妙具
 */
@RestController
@RequestMapping("/relationship")
public class RelationshipController extends BaseController {
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private UserService userService;

    @GetMapping("/getMyRelationship")
    public R getMyRelationship(){
        RelationshipVo relationshipVo = new RelationshipVo();
        relationshipVo.setUser(getUserId());
        relationshipVo.setStatus(DictionaryCode.RELATIONSHIP_STATUS_1);
        relationshipVo.setType(DictionaryCode.RELATIONSHIP_TYPE_1);
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",relationshipService.searchRelationship(relationshipVo));
        return R.success(result);
    }

    @GetMapping("/getMyApplicationList")
    public R getMyApplicationList(){
        RelationshipVo relationshipVo = new RelationshipVo();
        relationshipVo.setUser(getUserId());
//        relationshipVo.setStatus(DictionaryCode.RELATIONSHIP_STATUS_1);
        relationshipVo.setExceptType(DictionaryCode.RELATIONSHIP_TYPE_2);
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",relationshipService.searchRelationship(relationshipVo));
        return R.success(result);
    }

    @PostMapping("/addRelationship")
    public R addRelationship(@RequestBody RelationshipVo relationshipVo) {
        relationshipVo.setUser(getUserId());
        if(relationshipVo.getAnother()==getUserId()){
            return R.fail("这是你自己啊!");
        }
        if(userService.findById(relationshipVo.getAnother())==null){
            return R.fail("用户不存在!");
        }

        List<RelationshipInfo> relationshipInfos = relationshipService.searchRelationship(relationshipVo);  // 获取所有关系记录

        for (RelationshipInfo relationshipInfo:relationshipInfos){
            if(Objects.equals(relationshipInfo.getStatus().getId(), DictionaryCode.RELATIONSHIP_STATUS_1)){
                if(Objects.equals(relationshipInfo.getType().getId(), DictionaryCode.RELATIONSHIP_TYPE_1)){
                    return R.success("你们已经是好友了!");
                }
                else if(Objects.equals(relationshipInfo.getType().getId(),DictionaryCode.RELATIONSHIP_TYPE_2)){
                    return R.success("你不能申请与此用户为好友!");
                }
            }
            else if(Objects.equals(relationshipInfo.getStatus().getId(), DictionaryCode.RELATIONSHIP_STATUS_2)){
                return R.success("请勿重复申请!");
            }
        }


        relationshipVo.setStatus(DictionaryCode.RELATIONSHIP_STATUS_2);
        relationshipVo.setType(DictionaryCode.RELATIONSHIP_TYPE_1);

        relationshipService.add(relationshipVo);
        return R.success("申请中!请等待对方回应!");
    }

    @PostMapping("/agree")
    public R agree(@RequestBody RelationshipVo relationshipVo){
        relationshipVo.setUser(getUserId());
        relationshipVo.setStatus(DictionaryCode.RELATIONSHIP_STATUS_1);
        relationshipVo.setOldStatus(DictionaryCode.RELATIONSHIP_STATUS_2);
        relationshipVo.setType(DictionaryCode.RELATIONSHIP_TYPE_1);
        relationshipService.update(relationshipVo);
        return R.success("已同意!");
    }

    /**
     * 已撤销
     * @param relationshipVo
     * @return
     */
    @PostMapping("/revoke")
    public R revoke(@RequestBody RelationshipVo relationshipVo){
        relationshipVo.setUser(getUserId());
        relationshipVo.setStatus(DictionaryCode.RELATIONSHIP_STATUS_4);
        relationshipVo.setOldStatus(DictionaryCode.RELATIONSHIP_STATUS_2);
        relationshipVo.setType(DictionaryCode.RELATIONSHIP_TYPE_1);
        relationshipService.update(relationshipVo);
        return R.success("已撤销!");
    }

    /**
     * 拒绝
     * @param relationshipVo
     * @return
     */
    @PostMapping("/refuse")
    public R refuse(@RequestBody RelationshipVo relationshipVo){
        relationshipVo.setUser(getUserId());
        relationshipVo.setStatus(DictionaryCode.RELATIONSHIP_STATUS_5);
        relationshipVo.setOldStatus(DictionaryCode.RELATIONSHIP_STATUS_2);
        relationshipVo.setType(DictionaryCode.RELATIONSHIP_TYPE_1);
        relationshipService.update(relationshipVo);
        return R.success("已拒绝!");
    }

    @PostMapping("/delete")
    public R delete(@RequestBody RelationshipVo relationshipVo){
        relationshipVo.setUser(getUserId());
        relationshipVo.setStatus(DictionaryCode.RELATIONSHIP_STATUS_3);
        relationshipVo.setOldStatus(DictionaryCode.RELATIONSHIP_STATUS_1);
        relationshipVo.setType(DictionaryCode.RELATIONSHIP_TYPE_1);
        relationshipService.update(relationshipVo);
        return R.success("已解除!");
    }
}
