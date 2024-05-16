package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

@TableName("chinese_chess")
public class ChineseChess extends BaseObject {
    private Integer rPlayer;  // 红方(房主)
    private Integer rPlayerSkill;  // 红方技能
    private Integer bPlayer;  // 黑方
    private Integer bPlayerSkill;  // 黑方技能
    private Integer winner;  //胜方

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
