package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Team;

public interface TeamService extends IService<Team> {
    Team findBy(Team team);

    Team findById(Integer id);

    Team findByScid(String scid);

    void add(Team newTeam);

    void update(Team team);
}
