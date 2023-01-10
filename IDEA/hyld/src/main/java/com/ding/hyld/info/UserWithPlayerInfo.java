package com.ding.hyld.info;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.entity.Player;
import com.ding.hyld.entity.User;
import com.ding.hyld.entity.base.BaseObject;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;

public class UserWithPlayerInfo{
    private Integer id;
    private Integer userId;
    private UserInfo user;
    private Integer playerId;
    private PlayerInfo player;
    private Integer relationStatus;
    private Dictionary relation;
    private String note;
    private Dictionary checkStatus;
    private String playerMainPage; // 个人主页
    private String playerMainPageUrl; // 个人主页
    private String playerPreparePage; // 个人准备页
    private String playerPreparePageUrl; // 个人准备页
    private LocalDateTime createTime;
    private String createTimeStr;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public PlayerInfo getPlayer() {
        return player;
    }

    public void setPlayer(PlayerInfo player) {
        this.player = player;
    }

    public Dictionary getRelation() {
        return relation;
    }

    public void setRelation(Dictionary relation) {
        this.relation = relation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Dictionary getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Dictionary checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getPlayerMainPage() {
        return playerMainPage;
    }

    public void setPlayerMainPage(String playerMainPage) {
        this.playerMainPage = playerMainPage;
        setPlayerMainPageUrl(ResourcesPathUtils.getPhotoPath() + playerMainPage);
    }

    public String getPlayerPreparePage() {
        return playerPreparePage;
    }

    public void setPlayerPreparePage(String playerPreparePage) {
        this.playerPreparePage = playerPreparePage;
        setPlayerPreparePageUrl(ResourcesPathUtils.getPhotoPath() + playerPreparePage);
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        setCreateTimeStr(TimeUtils.toString(this.createTime,TimeUtils.FORMAT_1));
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getPlayerMainPageUrl() {
        return playerMainPageUrl;
    }

    public void setPlayerMainPageUrl(String playerMainPageUrl) {
        this.playerMainPageUrl = playerMainPageUrl;
    }

    public String getPlayerPreparePageUrl() {
        return playerPreparePageUrl;
    }

    public void setPlayerPreparePageUrl(String playerPreparePageUrl) {
        this.playerPreparePageUrl = playerPreparePageUrl;
    }

    @Override
    public String toString() {
        return "UserWithPlayerInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", user=" + user +
                ", playerId=" + playerId +
                ", player=" + player +
                ", relationStatus=" + relationStatus +
                ", relation=" + relation +
                ", note='" + note + '\'' +
                ", checkStatus=" + checkStatus +
                ", playerMainPage='" + playerMainPage + '\'' +
                ", playerMainPageUrl='" + playerMainPageUrl + '\'' +
                ", playerPreparePage='" + playerPreparePage + '\'' +
                ", playerPreparePageUrl='" + playerPreparePageUrl + '\'' +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                '}';
    }
}
