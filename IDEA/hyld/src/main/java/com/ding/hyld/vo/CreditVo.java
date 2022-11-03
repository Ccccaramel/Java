package com.ding.hyld.vo;

import com.ding.hyld.utils.TimeUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 返回积分统计结果
 */
public class CreditVo {
    private Integer uwtId; // 用户战队关联id
    private Integer teamId; // 战队id
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
    private String settlementTime; // 结算时间
    private String settlementTimeStr; // 结算时间 yyyy-MM-dd HH:mm:ss
    private String settlementTimeDate; // 结算时间 yyyy-MM-dd
    private Integer playerId; // 玩家id
    private Integer teamCompetitionTypeId; // 战队赛类型

    public Integer getUwtId() {
        return uwtId;
    }

    public void setUwtId(Integer uwtId) {
        this.uwtId = uwtId;
    }

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

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
//        DateTimeFormatter dateTimeFormatter1=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        this.settlementTimeStr = dateTimeFormatter1.format(settlementTime);
//        DateTimeFormatter dateTimeFormatter2=DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        this.settlementTimeDate = dateTimeFormatter2.format(settlementTime);
    }

    public Integer getRestTimes() {
        return restTimes;
    }

    public void setRestTimes(Integer restTimes) {
        this.restTimes = restTimes;
    }

    public String getSettlementTimeStr() {
        return settlementTimeStr;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getTeamCompetitionTypeId() {
        return teamCompetitionTypeId;
    }

    public void setTeamCompetitionTypeId(Integer teamCompetitionTypeId) {
        this.teamCompetitionTypeId = teamCompetitionTypeId;
    }
}
