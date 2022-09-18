package com.ding.hyld.info;

import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;

/**
 * 个人积分
 */
public class TeamMemberCreditInfo {
    private Integer creditId; // 积分id
    private String scid; // 玩家scid
    private String playerName; // 玩家昵称
    private LocalDateTime settlementTime; // 结算计算时间
    private String settlementTimeStr; // 结算计算时间(字符串)
    private String settlementTimeShowFormat; // 结算计算时间(显示格式)
    private Integer credit; // 积分
    private Integer creditTypeId; // 积分类型Id
    private Dictionary creditType; // 积分类型
    private String note; // 备注
    private String teamName; // 所属战队名称
    private Integer teamMemberId;

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

    public LocalDateTime getSettlementTime() {
        return settlementTime;
    }

    public String getSettlementTimeStr() {
        return settlementTimeStr;
    }

    public void setSettlementTimeStr(String settlementTimeStr) {
        this.settlementTimeStr = settlementTimeStr;
    }

    public void setSettlementTime(LocalDateTime settlementTime) {
        this.settlementTime = settlementTime;
        setSettlementTimeShowFormat(TimeUtils.toString(settlementTime));
        setSettlementTimeStr(settlementTime.toString());
    }

    public String getSettlementTimeShowFormat() {
        return settlementTimeShowFormat;
    }

    public void setSettlementTimeShowFormat(String settlementTimeShowFormat) {
        this.settlementTimeShowFormat = settlementTimeShowFormat;
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
}