package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.hyld.entity.base.BaseObject;

import java.time.LocalDateTime;

@TableName("team_with_player")
public class TeamWithPlayer extends BaseObject {
    private Integer playerId;
    private Integer teamId;
    private Integer teamMemberStatus;
    private LocalDateTime joinTime;
    private Integer joinWay;
    private LocalDateTime leaveTime;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamMemberStatus() {
        return teamMemberStatus;
    }

    public void setTeamMemberStatus(Integer teamMemberStatus) {
        this.teamMemberStatus = teamMemberStatus;
    }

    public LocalDateTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }

    public Integer getJoinWay() {
        return joinWay;
    }

    public void setJoinWay(Integer joinWay) {
        this.joinWay = joinWay;
    }

    public LocalDateTime getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }
}
