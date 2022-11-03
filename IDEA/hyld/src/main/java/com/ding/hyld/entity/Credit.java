package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.hyld.entity.base.BaseObject;

import java.time.LocalDateTime;

@TableName("credit")
public class Credit extends BaseObject {
    private Integer teamPlayerId;
    private LocalDateTime settlementTime;
    private Integer credit;
    private Integer creditType;
    private Integer teamCompetitionType;
    private Integer uwtId;

    public Integer getTeamPlayerId() {
        return teamPlayerId;
    }

    public void setTeamPlayerId(Integer teamPlayerId) {
        this.teamPlayerId = teamPlayerId;
    }

    public LocalDateTime getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(LocalDateTime settlementTime) {
        this.settlementTime = settlementTime;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getCreditType() {
        return creditType;
    }

    public void setCreditType(Integer creditType) {
        this.creditType = creditType;
    }

    public Integer getTeamCompetitionType() {
        return teamCompetitionType;
    }

    public void setTeamCompetitionType(Integer teamCompetitionType) {
        this.teamCompetitionType = teamCompetitionType;
    }

    public Integer getUwtId() {
        return uwtId;
    }

    public void setUwtId(Integer uwtId) {
        this.uwtId = uwtId;
    }
}
