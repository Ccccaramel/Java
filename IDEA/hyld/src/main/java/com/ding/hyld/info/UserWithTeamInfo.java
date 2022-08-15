package com.ding.hyld.info;

import com.ding.hyld.entity.BaseObject;

public class UserWithTeamInfo extends BaseObject {
    private boolean add;
    private Integer teamId;
    private String teamName;
    private String teamScid;
    private Integer userWithTeamStatus;
    private Integer teamEliminationLine;
    private Integer teamExcellentLine;
    private String teamNote;
    private String userNote;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamScid() {
        return teamScid;
    }

    public void setTeamScid(String teamScid) {
        this.teamScid = teamScid;
    }

    public Integer getUserWithTeamStatus() {
        return userWithTeamStatus;
    }

    public void setUserWithTeamStatus(Integer userWithTeamStatus) {
        this.userWithTeamStatus = userWithTeamStatus;
    }

    public Integer getTeamEliminationLine() {
        return teamEliminationLine;
    }

    public void setTeamEliminationLine(Integer teamEliminationLine) {
        this.teamEliminationLine = teamEliminationLine;
    }

    public Integer getTeamExcellentLine() {
        return teamExcellentLine;
    }

    public void setTeamExcellentLine(Integer teamExcellentLine) {
        this.teamExcellentLine = teamExcellentLine;
    }

    public String getTeamNote() {
        return teamNote;
    }

    public void setTeamNote(String teamNote) {
        this.teamNote = teamNote;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}
