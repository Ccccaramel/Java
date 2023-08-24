package com.ding.hyld.vo;

import java.time.LocalDateTime;

public class UserWithTeamVo {
    private Integer id;
    private LocalDateTime createTime;
    private String note;
    private boolean add;
    private Integer teamId;
    private String teamName;
    private String teamScid;
    private Integer teamStatus;
    private Integer userWithTeamStatus;
    private Integer teamEliminationLine;
    private Integer teamExcellentLine;
    private String teamNote;
    private String userNote;
    private Integer teamType;
    private Integer eliminationLine; // 淘汰线
    private Integer userId;
    private String userName;
    private Integer relationStatus;
    private Integer checkStatus;
    private Integer playerPosition; // 战队职位
    private boolean allRelieve; // 解除关联时,如果为 true ,则表明队长解除了关联,那么该战队所有副队长也同时解除

    private String playerScid; // 游戏账号scid
    private Integer teamMemberStatus; // 战队成员状态

    private Integer parentId;

    private Integer newUserId; // 用于交接战队

    private Integer creditScore; // 信誉积分

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

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

    public Integer getTeamType() {
        return teamType;
    }

    public void setTeamType(Integer teamType) {
        this.teamType = teamType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Integer relationStatus) {
        this.relationStatus = relationStatus;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Integer playerPosition) {
        this.playerPosition = playerPosition;
    }

    public boolean isAllRelieve() {
        return allRelieve;
    }

    public void setAllRelieve(boolean allRelieve) {
        this.allRelieve = allRelieve;
    }

    public Integer getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(Integer teamStatus) {
        this.teamStatus = teamStatus;
    }

    public String getPlayerScid() {
        return playerScid;
    }

    public void setPlayerScid(String playerScid) {
        this.playerScid = playerScid;
    }

    public Integer getTeamMemberStatus() {
        return teamMemberStatus;
    }

    public void setTeamMemberStatus(Integer teamMemberStatus) {
        this.teamMemberStatus = teamMemberStatus;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(Integer newUserId) {
        this.newUserId = newUserId;
    }

    public Integer getEliminationLine() {
        return eliminationLine;
    }

    public void setEliminationLine(Integer eliminationLine) {
        this.eliminationLine = eliminationLine;
    }
}
