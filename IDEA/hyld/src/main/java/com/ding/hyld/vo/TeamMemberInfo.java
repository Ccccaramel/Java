package com.ding.hyld.vo;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class TeamMemberInfo {
    private Integer teamId;
    private String name;
    private String scid;
    private Integer joinWay;
    private LocalDateTime time;
    private String note;
    private Integer playerId;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
        this.scid = scid;
    }

    public Integer getJoinWay() {
        return joinWay;
    }

    public void setJoinWay(Integer joinWay) {
        this.joinWay = joinWay;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
