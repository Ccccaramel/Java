package com.ding.hyld.vo;

public class ChineseChessVo {
    private Integer id;

    private Integer rPlayer;  // 红方(房主)
    private Integer rPlayerSkill;  // 红方技能
    private Integer bPlayer;  // 黑方
    private Integer bPlayerSkill;  // 黑方技能
    private Integer winner;  //胜方

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
}
