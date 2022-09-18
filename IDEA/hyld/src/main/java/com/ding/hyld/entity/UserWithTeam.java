package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.hyld.entity.base.BaseObject;

@TableName("user_with_team")
public class UserWithTeam extends BaseObject {
    private Integer userId;
    private Integer teamId;
    private Integer relationStatus; // 关联状态
    private Integer playerPosition; // 职位
    private Integer checkStatus; // 验证状态
    private String controllerPreparePage; // 管理者准备界面
    private String teamMainPage; // 战队界面


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

    public Integer getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Integer relationStatus) {
        this.relationStatus = relationStatus;
    }

    public Integer getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Integer playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getControllerPreparePage() {
        return controllerPreparePage;
    }

    public void setControllerPreparePage(String controllerPreparePage) {
        this.controllerPreparePage = controllerPreparePage;
    }

    public String getTeamMainPage() {
        return teamMainPage;
    }

    public void setTeamMainPage(String teamMainPage) {
        this.teamMainPage = teamMainPage;
    }
}
