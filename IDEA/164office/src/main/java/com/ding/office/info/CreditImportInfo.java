package com.ding.office.info;

public class CreditImportInfo {
    private Integer teamId;
    private Integer teamCompetitionTypeId;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamCompetitionTypeId() {
        return teamCompetitionTypeId;
    }

    public void setTeamCompetitionTypeId(Integer teamCompetitionTypeId) {
        this.teamCompetitionTypeId = teamCompetitionTypeId;
    }

    @Override
    public String toString() {
        return "CreditImportInfo{" +
                "teamId=" + teamId +
                ", teamCompetitionTypeId=" + teamCompetitionTypeId +
                '}';
    }
}
