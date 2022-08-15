package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeamMapper extends BaseMapper<Team> {
    Team findBy(@Param("team") Team team);

    Team findByScid(String scid);

    Team findById(Integer id);

    void add(@Param("newTeam") Team newTeam);

    void update(@Param("team") Team team);
}
