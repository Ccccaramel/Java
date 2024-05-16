package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.GameRoleComment;
import com.ding.office.info.GameRoleCommentInfo;
import com.ding.office.vo.GameRoleCommentVo;
import com.ding.office.vo.Page;
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

    Integer getGameRoleCommentDataOfPage(@Param("gameRoleCommentVo") GameRoleCommentVo gameRoleCommentVo, @Param("onlyFloor") boolean onlyFloor);

    Integer getAllGameRoleCommentOfPage(@Param("gameRoleCommentVo") GameRoleCommentVo gameRoleCommentVo);
}
