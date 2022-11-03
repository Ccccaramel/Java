package com.ding.hyld.controller;

import com.alibaba.fastjson.JSONObject;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.info.GameRoleInfo;
import com.ding.hyld.info.GameRolePopularityInfo;
import com.ding.hyld.service.GameRolePopularityService;
import com.ding.hyld.service.StarPowerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.GameRolePopularityVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gameRolePopularity")
public class GameRolePopularityController extends BaseController {
    @Autowired
    private GameRolePopularityService gameRolePopularityService;

    @PostMapping("/saveGameRolePopularity")
    public R saveGameRolePopularity(@RequestBody GameRolePopularityVo gameRolePopularityVo){
        gameRolePopularityVo.setUserId(getUserId());
        gameRolePopularityService.delete(gameRolePopularityVo); // 删除用户对该游戏角色的态度
        if(gameRolePopularityVo.getLike()==1){
            gameRolePopularityVo.setType(DictionaryCode.POPULARITY_TYPE_1);
            gameRolePopularityService.add(gameRolePopularityVo);
            return R.success("投票【喜欢】成功!");
        }else if(gameRolePopularityVo.getLike()==2){
            gameRolePopularityVo.setType(DictionaryCode.POPULARITY_TYPE_2);
            gameRolePopularityService.add(gameRolePopularityVo);
            return R.success("投票【不喜欢】成功!");
        }
        return R.success("投票已撤回!");
    }
    @GetMapping("/findByGameRoleId")
    public R findByGameRoleId(GameRolePopularityVo gameRolePopularityVo){
        gameRolePopularityVo.setUserId(getUserId());
        return R.success(gameRolePopularityService.findByGameRoleId(gameRolePopularityVo));
    }

    @GetMapping("/searchGameRolePopularity")
    public R searchStarPower(boolean pop){
        List<GameRolePopularityInfo> gameRolePopularityInfoList = gameRolePopularityService.searchGameRolePopularity(pop ? DictionaryCode.POPULARITY_TYPE_1 : DictionaryCode.POPULARITY_TYPE_2);
        boolean flag = true;
        Integer max = 0;
        for (GameRolePopularityInfo gameRolePopularityInfo:gameRolePopularityInfoList) {
            if(flag){
                gameRolePopularityInfo.setProportion(100);
                flag =false;
                max = gameRolePopularityInfo.getTotal();
            }else{
                gameRolePopularityInfo.setProportion((100*gameRolePopularityInfo.getTotal()/max));
            }
        }
        return R.success(gameRolePopularityInfoList);
    }
}
