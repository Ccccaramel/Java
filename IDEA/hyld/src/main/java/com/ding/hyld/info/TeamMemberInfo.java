package com.ding.hyld.info;

import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;

public class TeamMemberInfo {
    private Integer playerId;
    private Integer teamMemberId;
    private String scid;
    private String name;
    private String joinTime;
    private String joinWay;
    private String status;
    private Integer statusId;
    private String leaveTime;
    private String note;
    private String teamScid;
    private Integer teamId;
    private Integer teamMemberStatusId;
    private LocalDateTime time;
    private Integer type;

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

    public String getJoinWay() {
        return joinWay;
    }

    public void setJoinWay(String joinWay) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(Integer teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = TimeUtils.toString(joinTime);
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getTeamScid() {
        return teamScid;
    }

    public void setTeamScid(String teamScid) {
        this.teamScid = teamScid;
    }

    public Integer getTeamMemberStatusId() {
        return teamMemberStatusId;
    }

    public void setTeamMemberStatusId(Integer teamMemberStatusId) {
        this.teamMemberStatusId = teamMemberStatusId;
    }
}
