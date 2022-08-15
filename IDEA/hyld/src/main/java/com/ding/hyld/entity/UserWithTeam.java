package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

@TableName("user_with_team")
public class UserWithTeam extends BaseObject{
    private Integer userId;
    private Integer teamId;
    private Integer status;

    @TableField(exist = false)
    private String teamName;

    @TableField(exist = false)
    private String teamNote; // 战队信息备注

    @TableField(exist = false)
    private Dictionary teamStatus; // 战队状态

    @TableField(exist = false)
    private Dictionary uwtStatus; // 关联状态

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Team team;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Dictionary getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(Dictionary teamStatus) {
        this.teamStatus = teamStatus;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamNote() {
        return teamNote;
    }

    public void setTeamNote(String teamNote) {
        this.teamNote = teamNote;
    }

    public Dictionary getUwtStatus() {
        return uwtStatus;
    }

    public void setUwtStatus(Dictionary uwtStatus) {
        this.uwtStatus = uwtStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
