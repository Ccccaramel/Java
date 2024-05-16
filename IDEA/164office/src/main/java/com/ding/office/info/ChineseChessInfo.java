package com.ding.office.info;

import com.ding.office.utils.TimeUtils;

import java.time.LocalDateTime;

public class ChineseChessInfo {
    private Integer id;  // 红方(房主)
    private Integer rPlayer;  // 红方(房主)
    private Integer rPlayerSkill;  // 红方技能
    private Integer bPlayer;  // 黑方
    private Integer bPlayerSkill;  // 黑方技能
    private Integer winner;  //胜方
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    private Integer chanceOfWinning;  // 胜率,传给前端,会在末尾加上"%"
    private Integer totalGames;  // 总局数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getrPlayer() {
        return rPlayer;
    }

    public void setrPlayer(Integer rPlayer) {
        this.rPlayer = rPlayer;
    }

    public Integer getrPlayerSkill() {
        return rPlayerSkill;
    }

    public void setrPlayerSkill(Integer rPlayerSkill) {
        this.rPlayerSkill = rPlayerSkill;
    }

    public Integer getbPlayer() {
        return bPlayer;
    }

    public void setbPlayer(Integer bPlayer) {
        this.bPlayer = bPlayer;
    }

    public Integer getbPlayerSkill() {
        return bPlayerSkill;
    }

    public void setbPlayerSkill(Integer bPlayerSkill) {
        this.bPlayerSkill = bPlayerSkill;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
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
}
