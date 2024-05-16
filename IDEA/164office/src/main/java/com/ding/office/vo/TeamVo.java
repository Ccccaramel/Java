package com.ding.office.vo;

import com.ding.office.entity.Dictionary;

import java.time.LocalDateTime;

public class TeamVo {
    private Integer id;
    private LocalDateTime createTime;
    private String note;
    private String name;
    private String scid;
    private Integer status;
    private Integer eliminationLine;
    private Integer excellentLine;
    private Integer type; // 战队所在区

    private Dictionary teamType; // 战队所在区

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEliminationLine() {
        return eliminationLine;
    }

    public void setEliminationLine(Integer eliminationLine) {
        this.eliminationLine = eliminationLine;
    }

    public Integer getExcellentLine() {
        return excellentLine;
    }

    public void setExcellentLine(Integer excellentLine) {
        this.excellentLine = excellentLine;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Dictionary getTeamType() {
        return teamType;
    }

    public void setTeamType(Dictionary teamType) {
        this.teamType = teamType;
    }
}
