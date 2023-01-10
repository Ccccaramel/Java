package com.ding.hyld.info;

import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.utils.TimeUtils;
import com.ding.hyld.utils.Tree;

import java.time.LocalDateTime;
import java.util.List;

public class GameRoleCommentInfo extends Tree {
    private String text;
    private Integer floor;
    private UserInfo userInfo;
    private GameRoleInfo gameRoleInfo;
    private Dictionary status;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;
    private UserInfo replyUser; // 回复对象
    private String address;
    private Integer belongToFloor;

    private List<GameRoleCommentInfo> replyInfo;

    public GameRoleInfo getGameRoleInfo() {
        return gameRoleInfo;
    }

    public void setGameRoleInfo(GameRoleInfo gameRoleInfo) {
        this.gameRoleInfo = gameRoleInfo;
    }

    public Integer getBelongToFloor() {
        return belongToFloor;
    }

    public void setBelongToFloor(Integer belongToFloor) {
        this.belongToFloor = belongToFloor;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Dictionary getStatus() {
        return status;
    }

    public void setStatus(Dictionary status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        setCreateTimeStr(TimeUtils.toString(createTime,TimeUtils.FORMAT_1));
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public List<GameRoleCommentInfo> getReplyInfo() {
        return replyInfo;
    }

    public void setReplyInfo(List<GameRoleCommentInfo> replyInfo) {
        this.replyInfo = replyInfo;
    }

    public UserInfo getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(UserInfo replyUser) {
        this.replyUser = replyUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "GameRoleCommentInfo{" +
                "text='" + text + '\'' +
                ", floor=" + floor +
                ", userInfo=" + userInfo +
                ", gameRoleInfo=" + gameRoleInfo +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                ", replyUser=" + replyUser +
                ", address='" + address + '\'' +
                ", belongToFloor=" + belongToFloor +
                ", replyInfo=" + replyInfo +
                '}';
    }
}
