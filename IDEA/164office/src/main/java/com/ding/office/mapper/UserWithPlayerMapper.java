package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.UserWithPlayer;
import com.ding.office.info.UserWithPlayerInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.UserWithPlayerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserWithPlayerMapper extends BaseMapper<UserWithPlayer> {
    UserWithPlayer findBy(@Param("userWithPlayerVo") UserWithPlayerVo userWithPlayerVo);

    void add(@Param("userWithPlayerVo") UserWithPlayerVo userWithPlayerVo);

    List<UserWithPlayerInfo> searchRelation(@Param("userWithPlayerVo") UserWithPlayerVo userWithPlayerVo,@Param("page") Page page);

    void changeRelationStatus(Integer playerId, Integer userId, Integer relationStatus);

    void saveCheckInfo(Integer relationId, String playerMainPageNewName, String playerPreparePageNewName, Integer checkStatus);

    void updateCheckInfo(@Param("userWithPlayerVo") UserWithPlayerVo userWithPlayerVo);

    UserWithPlayerInfo findById(Integer id);

    Integer searchRelationOfPage(@Param("userWithPlayerVo") UserWithPlayerVo userWithPlayerVo);
}
