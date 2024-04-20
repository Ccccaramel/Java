package com.ding.hyld.info;

import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.utils.TimeUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public class ChatInfo {
    private Integer id;

    private String content;
    private UserInfo sender;
    private UserInfo recipient;
    private Dictionary status;
    private Dictionary type;

    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    private boolean isSelf;  // 是不是当前用户发送该信息

    private Integer currentWindows;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserInfo getSender() {
        return sender;
    }

    public void setSender(UserInfo sender) {
        this.sender = sender;
    }

    public UserInfo getRecipient() {
        return recipient;
    }

    public void setRecipient(UserInfo recipient) {
        this.recipient = recipient;
    }

    public Dictionary getStatus() {
        return status;
    }

    public void setStatus(Dictionary status) {
        this.status = status;
    }

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
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

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public Integer getCurrentWindows() {
        return currentWindows;
    }

    public void setCurrentWindows(Integer currentWindows) {
        this.currentWindows = currentWindows;
    }
}
