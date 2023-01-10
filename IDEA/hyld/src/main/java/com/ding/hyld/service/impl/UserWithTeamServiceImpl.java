package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.UserWithTeamVo;
import com.ding.hyld.mapper.UserWithTeamMapper;
import com.ding.hyld.service.UserWithTeamService;
import com.ding.hyld.vo.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserWithTeamServiceImpl extends ServiceImpl<UserWithTeamMapper, UserWithTeam> implements UserWithTeamService {
    @Override
    public List<UserWithTeamInfo> searchTeam(Page page, UserWithTeamVo userWithTeamVo) {
        return baseMapper.searchTeam(page, userWithTeamVo);
    }

    @Override
    public List<UserWithTeamInfo> findBy(UserWithTeamVo userWithTeamVo) {
        return baseMapper.findBy(userWithTeamVo);
    }

    @Override
    public void add(UserWithTeamVo userWithTeamVo) {
        baseMapper.add(userWithTeamVo);
    }

    @Override
    public void relieveTeam(UserWithTeamVo userWithTeamVo) {
        if(userWithTeamVo.isAllRelieve()){ // 队长解除与战队的关联,那么直接将与该战队的所有验证通过的关联置为验证失败状态
            baseMapper.allRelieveTeam(userWithTeamVo);
        }
        else{ // 定向解除|队长解除副队长
            baseMapper.relieveTeam(userWithTeamVo);
        }
    }

    @Override
    public void saveCheckInfo(Integer relationId, String controllerPreparePageNewName, String teamMainPageNewName, Integer checkStatus) {
        baseMapper.saveCheckInfo(relationId, controllerPreparePageNewName, teamMainPageNewName, checkStatus);
    }

    @Override
    public void teamExamineCheck(UserWithTeamVo userWithTeamVo) {
        if(DictionaryCode.CHECK_STATUS_3.equals(userWithTeamVo.getCheckStatus())){ // 如果验证通过,那么之前关联该战队所有验证通过的关联都将更改为待审核状态
            UserWithTeamVo oldUserWithTeamVo = new UserWithTeamVo();
            oldUserWithTeamVo.setTeamId(userWithTeamVo.getTeamId());
            oldUserWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
            oldUserWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_3);
            baseMapper.changeRelationStatus(oldUserWithTeamVo);
        }
        baseMapper.teamExamineCheck(userWithTeamVo);

    }

    @Override
    public void addViceCaptain(UserWithTeamVo userWithTeamVo) {
        baseMapper.addViceCaptain(userWithTeamVo);
    }

    @Override
    public List<UserWithTeamInfo> searchValidTeamInfo(Page page, UserWithTeamVo userWithTeamVo) {
        return baseMapper.searchValidTeamInfo(page, userWithTeamVo);
    }

    @Override
    public List<UserWithTeamInfo>  playerFindTeamBy(UserWithTeamVo userWithTeamVo) {
        return baseMapper.playerFindTeamBy(userWithTeamVo);
    }

    @Override
    public R teamTransfer(UserWithTeamVo userWithTeamVo) {
        userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
        List<UserWithTeamInfo> userWithTeamInfoList = findBy(userWithTeamVo);
        if(userWithTeamInfoList.size()!=1){ // 检查当前用户是否为该战队的队长
            return R.fail("非法请求!");
        }
        UserWithTeamVo vo = new UserWithTeamVo();
        vo.setUserId(userWithTeamVo.getNewUserId());
        vo.setTeamId(userWithTeamVo.getTeamId());
        vo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        vo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
        userWithTeamInfoList = findBy(vo);
        if(userWithTeamInfoList.size()>1){ // 检查当前用户是否为该战队的队员/副队长
            return R.fail("数据错误!请联系管理员协同解决");
        }
        else{
            if(userWithTeamInfoList.size()==1){ // 当前用户为该战队的队员/副队长,那么需要先切断该用户与战队的关联
                UserWithTeamInfo userWithTeamInfo = userWithTeamInfoList.get(0);
                UserWithTeamVo u = new UserWithTeamVo();
                u.setId(userWithTeamInfo.getId());
                u.setRelationStatus(DictionaryCode.RELATION_STATUS_3);
                relieveTeam(u);
            }
        }
        userWithTeamVo.setNote("由用户"+userWithTeamVo.getUserId()+"转让");
        userWithTeamVo.setUserId(userWithTeamVo.getNewUserId());
        baseMapper.teamTransfer(userWithTeamVo);
        return R.success("战队转让成功!");
    }

    @Override
    public UserWithTeamInfo findById(Integer id) {
        return baseMapper.findById(id);
    }

    @Override
    public void updateTeamCreditScore(UserWithTeamVo userWithTeamVo) {
        baseMapper.updateTeamCreditScore(userWithTeamVo);
    }

    @Override
    public Integer searchTeamOfPage(UserWithTeamVo userWithTeamVo) {
        return baseMapper.searchTeamOfPage(userWithTeamVo);
    }

    @Override
    public Integer searchValidTeamInfoOfPage(UserWithTeamVo userWithTeamVo) {
        return baseMapper.searchValidTeamInfoOfPage(userWithTeamVo);
    }

}
