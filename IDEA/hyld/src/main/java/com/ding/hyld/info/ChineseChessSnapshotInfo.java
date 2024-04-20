package com.ding.hyld.info;

import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;

public class ChineseChessSnapshotInfo {
    private Integer id;  // 红方(房主)

    private Integer chineseChess;  // 场次
    private Integer round;  // (第几)回合
    private Integer actor;  // 行动方 0:红方 1:黑方
    private Integer chess;  // 棋子 1-兵/卒, 2-炮/砲, 3-车, 4-马, 5-相/象, 6-士/仕, 7-帅/将
    private Integer oldLocationX;  // 原位置x
    private Integer oldLocationY;  // 原位置y
    private Integer newLocationX;  // 新位置x
    private Integer newLocationY;  // 位置y

    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChineseChess() {
        return chineseChess;
    }

    public void setChineseChess(Integer chineseChess) {
        this.chineseChess = chineseChess;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getActor() {
        return actor;
    }

    public void setActor(Integer actor) {
        this.actor = actor;
    }

    public Integer getChess() {
        return chess;
    }

    public void setChess(Integer chess) {
        this.chess = chess;
    }

    public Integer getOldLocationX() {
        return oldLocationX;
    }

    public void setOldLocationX(Integer oldLocationX) {
        this.oldLocationX = oldLocationX;
    }

    public Integer getOldLocationY() {
        return oldLocationY;
    }

    public void setOldLocationY(Integer oldLocationY) {
        this.oldLocationY = oldLocationY;
    }

    public Integer getNewLocationX() {
        return newLocationX;
    }

    public void setNewLocationX(Integer newLocationX) {
        this.newLocationX = newLocationX;
    }

    public Integer getNewLocationY() {
        return newLocationY;
    }

    public void setNewLocationY(Integer newLocationY) {
        this.newLocationY = newLocationY;
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
}
