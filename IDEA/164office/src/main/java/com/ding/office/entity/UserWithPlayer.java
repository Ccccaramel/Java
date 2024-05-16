package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

@TableName("user_with_player")
public class UserWithPlayer extends BaseObject {
    private Integer userId;
    private Integer playerId;
    private String playerMainPage; // 个人主页
    private String playerPreparePage; // 个人准备页
    private Integer relationStatus;
    private Integer checkStatus;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Integer relationStatus) {
        this.relationStatus = relationStatus;
    }

    public String getPlayerMainPage() {
        return playerMainPage;
    }

    public void setPlayerMainPage(String playerMainPage) {
        this.playerMainPage = playerMainPage;
    }

    public String getPlayerPreparePage() {
        return playerPreparePage;
    }

    public void setPlayerPreparePage(String playerPreparePage) {
        this.playerPreparePage = playerPreparePage;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }
}
