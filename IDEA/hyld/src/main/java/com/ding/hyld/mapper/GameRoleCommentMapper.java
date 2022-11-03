package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.GameRoleComment;
import com.ding.hyld.info.GameRoleCommentInfo;
import com.ding.hyld.vo.GameRoleCommentVo;
import com.ding.hyld.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameRoleCommentMapper extends BaseMapper<GameRoleComment> {
    void update(@Param("gameRoleCommentVo") GameRoleCommentVo gameRoleCommentVo);

    void add(@Param("gameRoleCommentVo") GameRoleCommentVo gameRoleCommentVo);

    List<GameRoleCommentInfo> getGameRoleCommentData(@Param("page") Page page, @Param("gameRoleCommentVo") GameRoleCommentVo gameRoleCommentVo, @Param("onlyFloor") boolean onlyFloor);

    void saveReplyGameRoleCommentInfo(@Param("gameRoleCommentVo") GameRoleCommentVo gameRoleCommentVo);



    GameRoleCommentInfo findByParentId(@Param("id") Integer id);

    void updateStatus(@Param("gameRoleCommentVo") GameRoleCommentVo gameRoleCommentVo);

    List<GameRoleCommentInfo> findChildren(@Param("floor") Integer floor, @Param("status")Integer status,@Param("gameRoleId") Integer gameRoleId);

    List<GameRoleCommentInfo> getAllGameRoleComment(@Param("page") Page page, @Param("gameRoleCommentVo") GameRoleCommentVo gameRoleCommentVo);
}
