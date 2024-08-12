package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

@TableName("chinese_chess")
public class ChineseChess extends BaseObject {
    private Integer name;  // 对战标题
    private Integer roomOwner;  // 房主(红方)
    private Integer roomOwnerSkill;  // 房主(红方)技能
    private Integer rival;  // 对手(黑方)
    private Integer rivalSkill;  // 对手(黑方)技能
    private Integer winner;  // 胜方
    private Integer type;  // 胜方战胜方式

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(Integer roomOwner) {
        this.roomOwner = roomOwner;
    }

    public Integer getRoomOwnerSkill() {
        return roomOwnerSkill;
    }

    public void setRoomOwnerSkill(Integer roomOwnerSkill) {
        this.roomOwnerSkill = roomOwnerSkill;
    }

    public Integer getRival() {
        return rival;
    }

    public void setRival(Integer rival) {
        this.rival = rival;
    }

    public Integer getRivalSkill() {
        return rivalSkill;
    }

    public void setRivalSkill(Integer rivalSkill) {
        this.rivalSkill = rivalSkill;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
