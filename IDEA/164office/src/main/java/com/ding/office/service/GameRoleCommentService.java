package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.GameRoleComment;
import com.ding.office.info.GameRoleCommentInfo;
import com.ding.office.vo.GameRoleCommentVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface GameRoleCommentService extends IService<GameRoleComment> {
    void update(GameRoleCommentVo gameRoleCommentVo);

    void add(GameRoleCommentVo gameRoleCommentVo);

    /**
     * onlyFloor:
     *   T:仅获取指定话题的所有楼层数据
     *   F:获取指定话题的所有楼层以及楼中楼数据
     * @param page
     * @param gameRoleCommentVo
     * @param onlyFloor
     * @return
     */
    List<GameRoleCommentInfo> getGameRoleCommentData(Page page, GameRoleCommentVo gameRoleCommentVo, boolean onlyFloor);

    void saveReplyGameRoleCommentInfo(GameRoleCommentVo gameRoleCommentVo);

    void updateStatus(GameRoleCommentVo gameRoleCommentVo);


    List<GameRoleCommentInfo> getAllGameRoleComment(Page page, GameRoleCommentVo gameRoleCommentVo);

    Integer getGameRoleCommentDataOfPage(GameRoleCommentVo gameRoleCommentVo, boolean onlyFloor);

    Integer getAllGameRoleCommentOfPage(GameRoleCommentVo gameRoleCommentVo);
}
