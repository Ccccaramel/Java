package com.ding.hyld.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.hyld.entity.base.BaseObject;
import com.ding.hyld.utils.ResourcesPathUtils;

import java.time.LocalDateTime;
public class UserWithPlayerVo{
    private Integer id;
    private LocalDateTime createTime;
    private String note;
    private Integer userId;
    private String userName;
    private Integer playerId;
    private Integer playerScid;
    private String playerName;
    private String personalInterfaceUrl;
    private String preparationInterfaceUrl;
    private Integer relationStatus;
    private Integer checkStatus;
    private Integer playerType;

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

    public String getPersonalInterfaceUrl() {
        return personalInterfaceUrl;
    }

    public void setPersonalInterfaceUrl(String personalInterfaceUrl) {
        this.personalInterfaceUrl = personalInterfaceUrl;
    }

    public String getPreparationInterfaceUrl() {
        return preparationInterfaceUrl;
    }

    public void setPreparationInterfaceUrl(String preparationInterfaceUrl) {
        this.preparationInterfaceUrl = preparationInterfaceUrl;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
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

    public String getUserName() {
        return userName;
    }

    public Integer getPlayerScid() {
        return playerScid;
    }

    public void setPlayerScid(Integer playerScid) {
        this.playerScid = playerScid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayerType() {
        return playerType;
    }

    public void setPlayerType(Integer playerType) {
        this.playerType = playerType;
    }
}
