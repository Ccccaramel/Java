package com.ding.hyld.info;

import com.ding.hyld.entity.Dictionary;

import java.time.LocalDateTime;

public class TeamInfo {
    private Integer id;
    private LocalDateTime createTime;
    private String note;
    private String name;
    private String scid;
    private Dictionary status;
    private Integer eliminationLine;
    private Integer excellentLine;
    private Dictionary type;

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

    public Dictionary getStatus() {
        return status;
    }

    public void setStatus(Dictionary status) {
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

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
    }
}
