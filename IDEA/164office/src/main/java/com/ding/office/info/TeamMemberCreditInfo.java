package com.ding.office.info;

import com.ding.office.entity.Dictionary;
import com.ding.office.utils.TimeUtils;

import java.time.LocalDateTime;

/**
 * 个人积分
 */
public class TeamMemberCreditInfo {
    private Integer creditId; // 积分id
    private String scid; // 玩家scid
    private String playerName; // 玩家昵称
    private String settlementTime; // 结算计算时间
    private String settlementTimeStr; // 结算计算时间(字符串)
    private Integer credit; // 积分
    private Integer creditTypeId; // 积分类型Id
    private Dictionary creditType; // 积分类型
    private String note; // 备注
    private String teamName; // 所属战队名称
    private Integer teamId; // 战队id
    private Integer teamMemberId; // 队员id
    private String teamMemberName; // 队员name
    private String playerScid; // 玩家scid
    private Integer averageCreditRank; // 平均积分排名
    private Double averageCredit; // 平均积分
    private Integer matchSettlementTimes; // 战队赛结算次数
    private Integer restTimes; // 战队赛请假次数
    private Integer matchTotalCredit; // 战队赛总积分
    private Integer exCredit; // 额外总积分
    private Integer totalCredit; // 总积分
    private Integer playerId; // 玩家id
    private String settlementTimeDate; // 结算时间 yyyy-MM-dd

    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
        this.scid = scid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public String getSettlementTimeStr() {
        return settlementTimeStr;
    }

    public void setSettlementTimeStr(String settlementTimeStr) {
        this.settlementTimeStr = settlementTimeStr;
    }

    public void setSettlementTime(LocalDateTime settlementTime) {
        setSettlementTimeStr(TimeUtils.toString(settlementTime,TimeUtils.FORMAT_1));
        this.settlementTime = settlementTime.toString();
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(Integer creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public Dictionary getCreditType() {
        return creditType;
    }

    public void setCreditType(Dictionary creditType) {
        this.creditType = creditType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(Integer teamMemberId) {
        this.teamMemberId = teamMemberId;
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

    public String getPlayerScid() {
        return playerScid;
    }

    public void setPlayerScid(String playerScid) {
        this.playerScid = playerScid;
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

    public Integer getRestTimes() {
        return restTimes;
    }

    public void setRestTimes(Integer restTimes) {
        this.restTimes = restTimes;
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

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getSettlementTimeDate() {
        return settlementTimeDate;
    }

    public void setSettlementTimeDate(String settlementTimeDate) {
        this.settlementTimeDate = settlementTimeDate;
    }

    @Override
    public String toString() {
        return "TeamMemberCreditInfo{" +
                "creditId=" + creditId +
                ", scid='" + scid + '\'' +
                ", playerName='" + playerName + '\'' +
                ", settlementTime='" + settlementTime + '\'' +
                ", settlementTimeStr='" + settlementTimeStr + '\'' +
                ", credit=" + credit +
                ", creditTypeId=" + creditTypeId +
                ", creditType=" + creditType +
                ", note='" + note + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamId=" + teamId +
                ", teamMemberId=" + teamMemberId +
                ", teamMemberName='" + teamMemberName + '\'' +
                ", playerScid='" + playerScid + '\'' +
                ", averageCreditRank=" + averageCreditRank +
                ", averageCredit=" + averageCredit +
                ", matchSettlementTimes=" + matchSettlementTimes +
                ", restTimes=" + restTimes +
                ", matchTotalCredit=" + matchTotalCredit +
                ", exCredit=" + exCredit +
                ", totalCredit=" + totalCredit +
                ", playerId=" + playerId +
                ", settlementTimeDate='" + settlementTimeDate + '\'' +
                '}';
    }
}
