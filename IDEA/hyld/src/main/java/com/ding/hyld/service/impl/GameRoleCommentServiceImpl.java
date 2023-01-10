package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.GameRoleComment;
import com.ding.hyld.info.GameRoleCommentInfo;
import com.ding.hyld.mapper.GameRoleCommentMapper;
import com.ding.hyld.service.GameRoleCommentService;
import com.ding.hyld.vo.GameRoleCommentVo;
import com.ding.hyld.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRoleCommentServiceImpl extends ServiceImpl<GameRoleCommentMapper, GameRoleComment> implements GameRoleCommentService {

    @Override
    public void update(GameRoleCommentVo gameRoleCommentVo) {
        baseMapper.update(gameRoleCommentVo);
    }

    @Override
    public void add(GameRoleCommentVo gameRoleCommentVo) {
        baseMapper.add(gameRoleCommentVo);
    }

    /**
     * 游戏角色评价分页数据
     * @param page
     * @param gameRoleCommentVo
     * @param onlyFloor
     * @return
     */
    @Override
    public List<GameRoleCommentInfo> getGameRoleCommentData(Page page, GameRoleCommentVo gameRoleCommentVo, boolean onlyFloor) {
        List<GameRoleCommentInfo> gameRoleCommentInfoList = baseMapper.getGameRoleCommentData(page, gameRoleCommentVo, onlyFloor); // 楼层
        /**
         * 获取每层的楼中楼并放入楼层中
         */
        for (GameRoleCommentInfo gameRoleCommentInfo:gameRoleCommentInfoList) {
            List<GameRoleCommentInfo> childrenList=baseMapper.findChildren(gameRoleCommentInfo.getFloor(), DictionaryCode.SPEECH_STATUS_1,gameRoleCommentInfo.getGameRoleInfo().getId());
            gameRoleCommentInfo.setReplyInfo(childrenList);

        }
        return gameRoleCommentInfoList;
    }



    @Override
    public void saveReplyGameRoleCommentInfo(GameRoleCommentVo gameRoleCommentVo) {
        baseMapper.saveReplyGameRoleCommentInfo(gameRoleCommentVo);
    }

    @Override
    public void updateStatus(GameRoleCommentVo gameRoleCommentVo) {
        baseMapper.updateStatus(gameRoleCommentVo);
    }

    @Override
    public List<GameRoleCommentInfo> getAllGameRoleComment(Page page, GameRoleCommentVo gameRoleCommentVo) {
        return baseMapper.getAllGameRoleComment(page,gameRoleCommentVo);
    }

    @Override
    public Integer getGameRoleCommentDataOfPage(GameRoleCommentVo gameRoleCommentVo, boolean onlyFloor) {
        return baseMapper.getGameRoleCommentDataOfPage(gameRoleCommentVo, onlyFloor);
    }

    @Override
    public Integer getAllGameRoleCommentOfPage(GameRoleCommentVo gameRoleCommentVo) {
        return baseMapper.getAllGameRoleCommentOfPage(gameRoleCommentVo);
    }
}
