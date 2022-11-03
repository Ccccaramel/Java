package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.info.GameRoleCommentInfo;
import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.service.GameRoleCommentService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.GameRoleCommentVo;
import com.ding.hyld.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gameRoleComment")
public class GameRoleCommentController extends BaseController {
    @Autowired
    private GameRoleCommentService gameRoleCommentService;

    /**
     * 获取制动话题的全部内容
     * @param page
     * @param gameRoleCommentVo
     * @return
     */
    @GetMapping("/getGameRoleCommentData")
    public R getGameRoleCommentData(Page page, GameRoleCommentVo gameRoleCommentVo){
        HashMap<String,Object> result=new HashMap<>();

        CurrentUser currentUser = getCurrentUser();
        if(gameRoleCommentVo.isShow() || currentUser==null || currentUser.getUser().getType().equals(DictionaryCode.USER_TYPE_2)){
            gameRoleCommentVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        }
        List<GameRoleCommentInfo> gameRoleCommentInfoList = gameRoleCommentService.getGameRoleCommentData(page, gameRoleCommentVo,true); // 仅所有楼层
        result.put("data",gameRoleCommentInfoList);
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(gameRoleCommentService.getGameRoleCommentData(null,gameRoleCommentVo,true).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 发表话题
     * @param gameRoleCommentVo
     * @return
     */
    @PostMapping("/saveGameRoleComment")
    public R saveGameRoleComment(@RequestBody GameRoleCommentVo gameRoleCommentVo){
        gameRoleCommentVo.setParentId(-1);
        gameRoleCommentVo.setUserId(getUserId());
        gameRoleCommentVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        gameRoleCommentService.add(gameRoleCommentVo);
        return R.success("发表成功!");
    }

    /**
     * 保存回复话题
     * @param gameRoleCommentVo
     * @return
     */
    @PostMapping("/saveReplyGameRoleCommentInfo")
    public R saveReplyGameRoleCommentInfo(@RequestBody GameRoleCommentVo gameRoleCommentVo){
        if(gameRoleCommentVo.isReply()){
            gameRoleCommentVo.setFloor(gameRoleCommentService.getGameRoleCommentData(null,gameRoleCommentVo,true).size()+1); // 放在开头,所有状态的回复都统计
            gameRoleCommentVo.setBelongToFloor(gameRoleCommentVo.getFloor());
        }
        gameRoleCommentVo.setUserId(getUserId());
        gameRoleCommentVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        gameRoleCommentService.saveReplyGameRoleCommentInfo(gameRoleCommentVo);
        return R.success("回复成功!");
    }



    /**
     * 获取所有游戏角色评价,不用分层
     * @param page
     * @param gameRoleCommentVo
     * @return
     */
    @GetMapping("/getAllGameRoleComment")
    public R getAllGameRoleComment(Page page, GameRoleCommentVo gameRoleCommentVo){
        HashMap<String,Object> result=new HashMap<>();
        CurrentUser currentUser = getCurrentUser();
        if( currentUser!=null && currentUser.getUser().getType().equals(DictionaryCode.USER_TYPE_1)){
            result.put("data",gameRoleCommentService.getAllGameRoleComment(page, gameRoleCommentVo));
            if(!Objects.equals(page.getSize(),null)){
                result.put("totalPage",Math.ceil(gameRoleCommentService.getAllGameRoleComment(null,gameRoleCommentVo).size()*1.0/page.getSize()));
            }
        }
        return R.success(result);
    }

    @PostMapping("/recoveryGameRoleComment")
    public R recoveryGameRoleComment(@RequestBody GameRoleCommentVo gameRoleCommentVo){
        CurrentUser currentUser = getCurrentUser();
        if( currentUser!=null && currentUser.getUser().getType().equals(DictionaryCode.USER_TYPE_1)){
            gameRoleCommentVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
            gameRoleCommentService.update(gameRoleCommentVo);
        }
        return R.success("已成功恢复!");
    }

    @PostMapping("/deleteGameRoleComment")
    public R deleteGameRoleComment(@RequestBody GameRoleCommentVo gameRoleCommentVo){
        CurrentUser currentUser = getCurrentUser();
        if( currentUser!=null && currentUser.getUser().getType().equals(DictionaryCode.USER_TYPE_1)){
            gameRoleCommentVo.setStatus(DictionaryCode.SPEECH_STATUS_3);
            gameRoleCommentService.update(gameRoleCommentVo);
        }
        return R.success("已删除!");
    }
}
