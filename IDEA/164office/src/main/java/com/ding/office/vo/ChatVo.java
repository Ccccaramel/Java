package com.ding.office.vo;

public class ChatVo {
    private Integer id;
    private String content;
    private Integer sender;
    private Integer recipient;
    private Integer status;
    private Integer type;
    private String note;

    private Integer user;  // 执行查询的用户
    private boolean isHall;  // 游客请求大厅的聊天数据
    private Integer currentWindows;  // 发送该消息的窗口
    private String token;

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

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getRecipient() {
        return recipient;
    }

    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public boolean isHall() {
        return isHall;
    }

    public void setHall(boolean hall) {
        isHall = hall;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCurrentWindows() {
        return currentWindows;
    }

    public void setCurrentWindows(Integer currentWindows) {
        this.currentWindows = currentWindows;
    }

}
