package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.UserWithPlayer;
import com.ding.hyld.info.UserWithPlayerInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserWithPlayerVo;
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
}
