package com.ding.office.vo;

public class ChineseChessVo {
    private Integer id;
    private String name;  // 对战标题
    private Integer roomOwner;  // 红方(房主)
    private Integer roomOwnerSkill;  // 红方技能
    private Integer rival;  // 黑方
    private Integer rivalSkill;  // 黑方技能
    private Integer winner;  //胜方
    private String note;
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
