package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

@TableName("credit")
public class Credit extends BaseObject {
    private Integer teamPlayerId;
    private LocalDateTime settlementTime;
    private Integer credit;
    private Integer creditType;

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
}
