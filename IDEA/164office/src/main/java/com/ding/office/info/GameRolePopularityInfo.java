package com.ding.office.info;

import com.ding.office.entity.Dictionary;
import com.ding.office.utils.TimeUtils;

import java.time.LocalDateTime;

public class GameRolePopularityInfo {
    private GameRoleInfo gameRole;
    private Integer gameRoleId;
    private Dictionary type;
    private UserInfo user;
    private Integer id;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;
    private Integer total;
    private Integer proportion;

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getGameRoleId() {
        return gameRoleId;
    }

    public void setGameRoleId(Integer gameRoleId) {
        this.gameRoleId = gameRoleId;
    }

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public GameRoleInfo getGameRole() {
        return gameRole;
    }

    public void setGameRole(GameRoleInfo gameRole) {
        this.gameRole = gameRole;
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
        this.setCreateTimeStr(TimeUtils.toString(createTime,TimeUtils.FORMAT_1));
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "GameRolePopularityInfo{" +
                "gameRole=" + gameRole +
                ", gameRoleId=" + gameRoleId +
                ", type=" + type +
                ", user=" + user +
                ", id=" + id +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                ", total=" + total +
                ", proportion=" + proportion +
                '}';
    }
}
