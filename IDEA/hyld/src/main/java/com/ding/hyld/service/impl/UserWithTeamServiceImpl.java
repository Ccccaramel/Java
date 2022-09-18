package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.UserWithTeamInfo;
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
    public void add(UserWithTeam userWithTeam) {
        baseMapper.add(userWithTeam);
    }

    @Override
    public void relieveTeam(UserWithTeamVo userWithTeamVo) {
        if(userWithTeamVo.isAllRelieve()){ // 队长解除与战队的关联,那么直接将与该战队的所有验证通过的关联置为验证失败状态
            baseMapper.allRelieveTeam(userWithTeamVo);
        }
        else{ // 队长解除副队长
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
            oldUserWithTeamVo.setOldCheckStatus(DictionaryCode.CHECK_STATUS_3);
            oldUserWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_1);
            baseMapper.changeCheckStatus(oldUserWithTeamVo);
        }
        baseMapper.teamExamineCheck(userWithTeamVo);

    }

    @Override
    public void addViceCaptain(UserWithTeamVo userWithTeamVo) {
        baseMapper.addViceCaptain(userWithTeamVo);
    }

}
