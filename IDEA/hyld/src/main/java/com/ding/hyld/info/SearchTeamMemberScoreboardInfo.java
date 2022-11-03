package com.ding.hyld.info;

public class SearchTeamMemberScoreboardInfo {
    private Integer uwtId;
    private Integer teamId;
    private Integer teamCompetitionTypeId; // 战队赛类型

    public Integer getUwtId() {
        return uwtId;
    }

    public void setUwtId(Integer uwtId) {
        this.uwtId = uwtId;
    }

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
}
