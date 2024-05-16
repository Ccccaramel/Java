package com.ding.office.controller;

import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.info.GameRolePopularityInfo;
import com.ding.office.service.GameRolePopularityService;
import com.ding.office.utils.R;
import com.ding.office.vo.GameRolePopularityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
