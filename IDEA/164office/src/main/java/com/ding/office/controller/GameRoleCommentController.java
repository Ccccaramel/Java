package com.ding.office.controller;

import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.info.GameRoleCommentInfo;
import com.ding.office.security.CurrentUser;
import com.ding.office.service.GameRoleCommentService;
import com.ding.office.service.impl.QQIPServiceImpl;
import com.ding.office.utils.IpUtils;
import com.ding.office.utils.R;
import com.ding.office.vo.GameRoleCommentVo;
import com.ding.office.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gameRoleComment")
public class GameRoleCommentController extends BaseController {
    @Autowired
    private GameRoleCommentService gameRoleCommentService;
    @Autowired
    private QQIPServiceImpl ibsService;
    /**
     * 获取指定话题的全部内容
     * @param page
     * @param gameRoleCommentVo
     * @return
     */
    @GetMapping("/getGameRoleCommentData")
    public R getGameRoleCommentData(Page page, GameRoleCommentVo gameRoleCommentVo){
        HashMap<String,Object> result=new HashMap<>();

        if(gameRoleCommentVo.isShow() || !(isLogin() && getCurrentUser().getUser().getRole().equals(DictionaryCode.USER_ROLE_2))){
            gameRoleCommentVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        }
        List<GameRoleCommentInfo> gameRoleCommentInfoList = gameRoleCommentService.getGameRoleCommentData(page, gameRoleCommentVo,true); // 仅所有楼层
        result.put("data",gameRoleCommentInfoList);
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(gameRoleCommentService.getGameRoleCommentDataOfPage(gameRoleCommentVo,true)*1.0/page.getSize()));
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
    public R saveReplyGameRoleCommentInfo(@RequestBody GameRoleCommentVo gameRoleCommentVo, HttpServletRequest request){
        if(gameRoleCommentVo.isReply()){
            gameRoleCommentVo.setFloor(gameRoleCommentService.getGameRoleCommentData(null,gameRoleCommentVo,true).size()+1); // 放在开头,所有状态的回复都统计
            gameRoleCommentVo.setBelongToFloor(gameRoleCommentVo.getFloor());
        }

        String ip = IpUtils.getIpAddress(request);
        gameRoleCommentVo.setIp(ip);
        gameRoleCommentVo.setAddress(ibsService.getAddress(ip).get("address"));

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
        if( currentUser!=null && currentUser.getUser().getRole().equals(DictionaryCode.USER_ROLE_1)){
            result.put("data",gameRoleCommentService.getAllGameRoleComment(page, gameRoleCommentVo));
            if(!Objects.equals(page.getSize(),null)){
                result.put("totalPage",Math.ceil(gameRoleCommentService.getAllGameRoleCommentOfPage(gameRoleCommentVo)*1.0/page.getSize()));
            }
        }
        return R.success(result);
    }

    @PreAuthorize("hasAuthority('gameRoleManage_gameRoleComment')")
    @PostMapping("/recoveryGameRoleComment")
    public R recoveryGameRoleComment(@RequestBody GameRoleCommentVo gameRoleCommentVo){
        CurrentUser currentUser = getCurrentUser();
        if( currentUser!=null && currentUser.getUser().getRole().equals(DictionaryCode.USER_ROLE_1)){
            gameRoleCommentVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
            gameRoleCommentService.update(gameRoleCommentVo);
        }
        return R.success("已成功恢复!");
    }

    @PreAuthorize("hasAuthority('gameRoleManage_gameRoleComment')")
    @PostMapping("/deleteGameRoleComment")
    public R deleteGameRoleComment(@RequestBody GameRoleCommentVo gameRoleCommentVo){
        CurrentUser currentUser = getCurrentUser();
        if( currentUser!=null && currentUser.getUser().getRole().equals(DictionaryCode.USER_ROLE_1)){
            gameRoleCommentVo.setStatus(DictionaryCode.SPEECH_STATUS_3);
            gameRoleCommentService.update(gameRoleCommentVo);
        }
        return R.success("已删除!");
    }
}
