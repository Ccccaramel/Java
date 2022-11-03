package com.ding.hyld.vo;

/**
 * 个人积分
 */
public class TeamMemberCreditVo {
    private Integer uwtId; // 用户战队关联id
    private Integer creditId; // 积分id
    private Integer teamMemberId; // 队员id
    private String scid; // 玩家scid
    private String playerName; // 玩家昵称
    private String settlementTime; // 结算计算时间
    private Integer credit; // 积分
    private Integer creditTypeId; // 积分类型Id
    private String note; // 备注
    private String teamName; // 所属战队名称
    private Integer teamCompetitionTypeId; // 战队竞赛类型

    public Integer getUwtId() {
        return uwtId;
    }

    public void setUwtId(Integer uwtId) {
        this.uwtId = uwtId;
    }

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

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
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

    public Integer getTeamCompetitionTypeId() {
        return teamCompetitionTypeId;
    }

    public void setTeamCompetitionTypeId(Integer teamCompetitionTypeId) {
        this.teamCompetitionTypeId = teamCompetitionTypeId;
    }

}
