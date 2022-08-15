package com.ding.hyld.vo;

import java.time.LocalDateTime;

/**
 * 返回积分统计结果
 */
public class CreditVo {
    private Integer teamId; // 队员id
    private Integer teamMemberId; // 队员id
    private String teamMemberName; // 队员name
    private String playerScid; // 玩家scid
    private String playerName; // 玩家昵称
    private Integer averageCreditRank; // 平均积分排名
    private Double averageCredit; // 平均积分
    private Integer matchSettlementTimes; // 战队赛结算次数
    private Integer restTimes; // 战队赛请假次数
    private Integer matchTotalCredit; // 战队赛总积分
    private Integer exCredit; // 额外总积分
    private Integer totalCredit; // 总积分
    private LocalDateTime settlementTime; // 结算时间

    public Integer getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(Integer teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public String getPlayerScid() {
        return playerScid;
    }

    public void setPlayerScid(String playerScid) {
        this.playerScid = playerScid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getAverageCreditRank() {
        return averageCreditRank;
    }

    public void setAverageCreditRank(Integer averageCreditRank) {
        this.averageCreditRank = averageCreditRank;
    }

    public Double getAverageCredit() {
        return averageCredit;
    }

    public void setAverageCredit(Double averageCredit) {
        this.averageCredit = averageCredit;
    }

    public Integer getMatchSettlementTimes() {
        return matchSettlementTimes;
    }

    public void setMatchSettlementTimes(Integer matchSettlementTimes) {
        this.matchSettlementTimes = matchSettlementTimes;
    }

    public Integer getMatchTotalCredit() {
        return matchTotalCredit;
    }

    public void setMatchTotalCredit(Integer matchTotalCredit) {
        this.matchTotalCredit = matchTotalCredit;
    }

    public Integer getExCredit() {
        return exCredit;
    }

    public void setExCredit(Integer exCredit) {
        this.exCredit = exCredit;
    }

    public Integer getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Integer totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamMemberName() {
        return teamMemberName;
    }

    public void setTeamMemberName(String teamMemberName) {
        this.teamMemberName = teamMemberName;
    }

    public LocalDateTime getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        if(!settlementTime.isEmpty()){
            this.settlementTime = LocalDateTime.parse(settlementTime);
        }
    }

    public Integer getRestTimes() {
        return restTimes;
    }

    public void setRestTimes(Integer restTimes) {
        this.restTimes = restTimes;
    }
}
