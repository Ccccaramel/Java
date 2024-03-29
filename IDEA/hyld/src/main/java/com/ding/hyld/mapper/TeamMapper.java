package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Team;
import com.ding.hyld.info.TeamInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMapper extends BaseMapper<Team> {

    TeamInfo findByScid(String scid);

    TeamInfo findById(Integer id);

    void add(@Param("teamVo") TeamVo teamVo);

    void update(@Param("teamVo") TeamVo teamVo);

    List<TeamInfo> searchTeamInfo(@Param("page") Page page, @Param("teamVo") TeamVo teamVo);

    Integer searchTeamInfoOfPage(@Param("teamVo") TeamVo teamVo);
}
