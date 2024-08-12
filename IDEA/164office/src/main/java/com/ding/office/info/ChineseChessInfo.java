package com.ding.office.info;

import com.ding.office.entity.Dictionary;
import com.ding.office.utils.TimeUtils;

import java.time.LocalDateTime;

public class ChineseChessInfo {
    private String name;  // 对战标题
    private Integer id;  // 场次编号
    private UserInfo roomOwner;  // 房主(红方)
    private Integer roomOwnerSkill;  // 房主(红方)技能
    private UserInfo rival;  // 对手(黑方)
    private Integer rivalSkill;  // 对手(黑方)技能
    private UserInfo winner;  //胜方
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    private Dictionary type;

    private Integer chanceOfWinning;  // 胜率,传给前端,会在末尾加上"%"
    private Integer totalGames;  // 总局数

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

    public UserInfo getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(UserInfo roomOwner) {
        this.roomOwner = roomOwner;
    }

    public Integer getRoomOwnerSkill() {
        return roomOwnerSkill;
    }

    public void setRoomOwnerSkill(Integer roomOwnerSkill) {
        this.roomOwnerSkill = roomOwnerSkill;
    }

    public UserInfo getRival() {
        return rival;
    }

    public void setRival(UserInfo rival) {
        this.rival = rival;
    }

    public Integer getRivalSkill() {
        return rivalSkill;
    }

    public void setRivalSkill(Integer rivalSkill) {
        this.rivalSkill = rivalSkill;
    }

    public UserInfo getWinner() {
        return winner;
    }

    public void setWinner(UserInfo winner) {
        this.winner = winner;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        setCreateTimeStr(TimeUtils.toString(createTime,TimeUtils.FORMAT_1));
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getChanceOfWinning() {
        return chanceOfWinning;
    }

    public void setChanceOfWinning(Integer chanceOfWinning) {
        this.chanceOfWinning = chanceOfWinning;
    }

    public Integer getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
    }
}
