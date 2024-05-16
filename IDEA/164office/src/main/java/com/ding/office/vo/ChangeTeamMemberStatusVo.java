package com.ding.office.vo;

import java.time.LocalDateTime;

public class ChangeTeamMemberStatusVo {
    private Integer teamMemberId; // 队员id
    private Integer teamMemberStatusId; // 状态类型id
    private String note; // 备注
    private LocalDateTime time; // 离队时间


    public Integer getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(Integer teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public Integer getTeamMemberStatusId() {
        return teamMemberStatusId;
    }

    public void setTeamMemberStatusId(Integer teamMemberStatusId) {
        this.teamMemberStatusId = teamMemberStatusId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
