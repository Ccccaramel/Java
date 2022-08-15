package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("team")
public class Team extends BaseObject {
    private String name;
    private String scid;
    private Integer status;
    private Integer eliminationLine;
    private Integer excellentLine;

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
}
