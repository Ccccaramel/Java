package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Team;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.SearchTeamInfo;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserWithTeamMapper extends BaseMapper<UserWithTeam> {

    List<Team> searchTeam(@Param("page") Page page,@Param("searchTeamInfo") SearchTeamInfo searchTeamInfo);

    List<UserWithTeam> findBy(@Param("newTeam") Team newTeam);

    List<UserWithTeam> findByTeamId(Integer teamId);

    void add(@Param("userWithTeam") UserWithTeam userWithTeam);

    void removeTeam(Integer userId, Integer teamId);

    UserWithTeam getBy(@Param("userWithTeamInfo") UserWithTeamInfo userWithTeamInfo);

    void update(@Param("userWithTeam") UserWithTeam userWithTeam);
}
