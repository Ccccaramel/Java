package com.ding.hyld.info;

import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.entity.Team;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;

public class UserWithTeamInfo {
    private Integer id;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;
    private Integer userId;
    private Integer teamId;
    private Integer relationStatus; // 关联状态
    private Integer playerPosition; // 职位
    private Dictionary checkStatus; // 验证状态

    private String teamName;

    private String teamNote; // 战队信息备注

    private Dictionary teamStatus; // 战队状态

    private Dictionary relation; // 关联状态

    private UserInfo user;

    private Team team;

    private Dictionary playerPositionType;

    private String controllerPreparePage; // 管理者准备界面
    private String teamMainPage; // 战队界面

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
        setCreateTimeStr(TimeUtils.toString(this.createTime));
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Dictionary getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(Dictionary teamStatus) {
        this.teamStatus = teamStatus;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamNote() {
        return teamNote;
    }

    public void setTeamNote(String teamNote) {
        this.teamNote = teamNote;
    }

    public Dictionary getRelation() {
        return relation;
    }

    public void setRelation(Dictionary relation) {
        this.relation = relation;
    }

    public Integer getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Integer relationStatus) {
        this.relationStatus = relationStatus;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Integer playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Dictionary getPlayerPositionType() {
        return playerPositionType;
    }

    public void setPlayerPositionType(Dictionary playerPositionType) {
        this.playerPositionType = playerPositionType;
    }

    public Dictionary getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Dictionary checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getControllerPreparePage() {
        return controllerPreparePage;
    }

    public void setControllerPreparePage(String controllerPreparePage) {
        this.controllerPreparePage = ResourcesPathUtils.getPhotoPath() + controllerPreparePage;
    }

    public String getTeamMainPage() {
        return teamMainPage;
    }

    public void setTeamMainPage(String teamMainPage) {
        this.teamMainPage = ResourcesPathUtils.getPhotoPath() + teamMainPage;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
