package com.ding.hyld.info;

import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;

public class PointerInfo {
    private Integer id;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;
    private String text;
    private String replyText;
    private String address;
    private UserInfo userInfo;
    private Dictionary status;
    private LocalDateTime replyTime;
    private String replyTimeStr;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public LocalDateTime getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(LocalDateTime replyTime) {
        this.replyTime = replyTime;
        this.setReplyTimeStr(TimeUtils.toString(this.replyTime,TimeUtils.FORMAT_1));
    }

    public String getReplyTimeStr() {
        return replyTimeStr;
    }

    public void setReplyTimeStr(String replyTimeStr) {
        this.replyTimeStr = replyTimeStr;
    }
}
