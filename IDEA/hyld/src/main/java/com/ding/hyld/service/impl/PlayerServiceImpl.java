package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.Player;
import com.ding.hyld.entity.UserWithPlayer;
import com.ding.hyld.mapper.PlayerMapper;
import com.ding.hyld.service.PlayerService;
import com.ding.hyld.service.UserWithPlayerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.PlayerVo;
import com.ding.hyld.vo.TeamMemberVo;
import com.ding.hyld.vo.UserWithPlayerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ding.hyld.info.PlayerInfo;

import java.util.List;

@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, Player> implements PlayerService {
    @Autowired
    private UserWithPlayerService userWithPlayerService;

    @Override
    public List<PlayerInfo> searchPlayerInfo(Page page, PlayerVo playerVo) {
        return baseMapper.searchPlayerInfo(page, playerVo);
    }

    @Override
    public Player addPlayer(TeamMemberVo teamMemberVo) {
        PlayerVo playerVo = new PlayerVo();
        playerVo.setScid(teamMemberVo.getScid());
        playerVo.setName(teamMemberVo.getName());
        playerVo.setType(teamMemberVo.getType());
        Player player=baseMapper.findBy(playerVo);
        if(player==null){
            baseMapper.addPlayer(playerVo);
        }
        return player;
    }

    /**
     * 用户新增关联游戏账号
     *   绑定与验证分开
     * @param playerVo
     * @param userId
     * @return
     */
    @Override
    public R userRelationPlayer(PlayerVo playerVo, Integer userId) {
        Player player = new Player();
        player.setScid(playerVo.getScid());
        player.setType(playerVo.getType());
        player.setName(playerVo.getName());
        Player playerRes=baseMapper.findBy(playerVo);
        if(playerRes==null){ // 游戏账号不存在,新增
            baseMapper.addPlayer(playerVo);
            add(userId,player.getId());
            return R.success("游戏账号绑定成功!");
        }
        else{ // 游戏账号已存在,那么需要判断该用户是否已经绑定
            player = playerRes;
            UserWithPlayerVo userWithPlayerVo = new UserWithPlayerVo();
            userWithPlayerVo.setUserId(userId);
            userWithPlayerVo.setPlayerId(player.getId());
            userWithPlayerVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
            UserWithPlayer userWithPlayerRes = userWithPlayerService.findBy(userWithPlayerVo);
            if(userWithPlayerRes==null){ // 游戏账号已存在且未重复绑定
                // 是否被其他用户绑定且已验证通过
                UserWithPlayerVo find=new UserWithPlayerVo();
                find.setPlayerId(player.getId());
                find.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
                if(userWithPlayerService.findBy(find)==null){ // 该游戏账号没有被其他用户绑定并审核通过
                    add(userId,player.getId());
                    return R.success("游戏账号绑定成功!");
                }else{
                    add(userId,player.getId());
                    return R.fail("游戏账号绑定成功!,但该游戏账号也已被其他用户绑定且验证审核通过,你仍然可以绑定该游戏账号,但无法修改任何信息,你可以申请验证,验证通过后即可修改,如有异议请加QQ群(475765701)@群主");
                }
            }else{
                return R.fail("你已绑定!请勿重复绑定!");
            }
        }
    }

    private void add(Integer userId,Integer playerId){
        UserWithPlayerVo userWithPlayerVo = new UserWithPlayerVo();
        userWithPlayerVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithPlayerVo.setCheckStatus(DictionaryCode.CHECK_STATUS_1);
        userWithPlayerVo.setUserId(userId);
        userWithPlayerVo.setPlayerId(playerId);
        userWithPlayerService.add(userWithPlayerVo);
    }

    @Override
    public R updateRelationPlayer(PlayerVo playerVo, Integer userId) {
        Player player=baseMapper.findBy(playerVo);
        if(player==null){
            return R.fail("你号要没了!");
        }else{
            UserWithPlayerVo userWithPlayerVo = new UserWithPlayerVo();
            userWithPlayerVo.setUserId(userId);
            userWithPlayerVo.setPlayerId(playerVo.getId());
            UserWithPlayer userWithPlayer = userWithPlayerService.findBy(userWithPlayerVo);
            if(userWithPlayer.getCheckStatus().equals(DictionaryCode.CHECK_STATUS_3)){ // 审核通过
                baseMapper.update(playerVo);
                return R.success("游戏账号信息修改成功!");
            }
            else{
                return R.fail("游戏账号关联审核未通过,无法编辑任何信息!请上传截图证明以开放修改权限!");
            }
        }
    }
}
