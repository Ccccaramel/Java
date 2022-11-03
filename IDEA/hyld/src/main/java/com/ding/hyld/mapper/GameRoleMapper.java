package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.GameRole;
import com.ding.hyld.info.GameRoleInfo;
import com.ding.hyld.vo.GameRoleVo;
import com.ding.hyld.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameRoleMapper extends BaseMapper<GameRole> {
    List<GameRoleInfo> searchGameRole(@Param("page") Page page, @Param("gameRoleVo") GameRoleVo gameRoleVo);

    void update(@Param("gameRoleVo") GameRoleVo gameRoleVo);

    void add(@Param("gameRoleVo") GameRoleVo gameRoleVo);

    List<GameRoleInfo> getAllGameRole();

    GameRoleInfo findById(Integer id);

    List<GameRoleInfo> getBasicForm(@Param("gameRoleVo") GameRoleVo gameRoleVo);

    GameRoleInfo searchGameRoleInfoById(@Param("gameRoleVo") GameRoleVo gameRoleVo);

    GameRoleInfo findSecondaryFormById(Integer id);
}
