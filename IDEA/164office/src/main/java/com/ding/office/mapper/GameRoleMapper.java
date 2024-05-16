package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.GameRole;
import com.ding.office.info.GameRoleInfo;
import com.ding.office.vo.GameRoleVo;
import com.ding.office.vo.Page;
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

    Integer searchGameRoleOfPage(@Param("gameRoleVo") GameRoleVo gameRoleVo);
}
