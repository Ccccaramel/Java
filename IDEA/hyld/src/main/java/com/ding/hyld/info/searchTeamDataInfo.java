package com.ding.hyld.info;

public class searchTeamDataInfo {
    private Integer teamId;
    private String settlementTime;
    private Integer teamCompetitionType;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
    }

    public Integer getTeamCompetitionType() {
        return teamCompetitionType;
    }

    public void setTeamCompetitionType(Integer teamCompetitionType) {
        this.teamCompetitionType = teamCompetitionType;
    }

    @Override
    public String toString() {
        return "searchTeamDataInfo{" +
                "teamId=" + teamId +
                ", settlementTime='" + settlementTime + '\'' +
                ", teamCompetitionType=" + teamCompetitionType +
                '}';
    }
}
