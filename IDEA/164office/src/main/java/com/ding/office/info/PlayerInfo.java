package com.ding.office.info;

import com.ding.office.entity.Dictionary;
import com.ding.office.utils.TimeUtils;

import java.time.LocalDateTime;

public class PlayerInfo {
    private Integer id;
    private String scid;
    private String name;
    private String note;
    private String type;
    private String createTime;
    private Dictionary playerType;
//    private String playerType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
        this.scid = scid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = TimeUtils.toString(createTime,TimeUtils.FORMAT_1);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Dictionary getPlayerType() {
        return playerType;
    }

    public void setPlayerType(Dictionary playerType) {
        this.playerType = playerType;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "id=" + id +
                ", scid='" + scid + '\'' +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", type='" + type + '\'' +
                ", createTime='" + createTime + '\'' +
                ", playerType=" + playerType +
                '}';
    }
}
