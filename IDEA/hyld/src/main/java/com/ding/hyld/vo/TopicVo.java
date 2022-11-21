package com.ding.hyld.vo;

import java.time.LocalDateTime;

public class TopicVo {
    private Integer id;
    private boolean add;
    private String title;
    private String rubric;
    private String text;
    private Integer parentId;
    private Integer userId;
    private String userName;
    private Integer floor;
    private Integer topicId;
    private Integer status;
    private LocalDateTime createTime;
    private String note;
    private boolean replyTopic; // T:回复话题 F:楼中楼
    private String ip;
    private String address;
    private Integer belongToFloor;
    private boolean show;
    private boolean manageMyTopic;
    private String images;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public String getRubric() {
        return rubric;
    }

    public void setRubric(String rubric) {
        this.rubric = rubric;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public boolean isReplyTopic() {
        return replyTopic;
    }

    public void setReplyTopic(boolean replyTopic) {
        this.replyTopic = replyTopic;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBelongToFloor() {
        return belongToFloor;
    }

    public void setBelongToFloor(Integer belongToFloor) {
        this.belongToFloor = belongToFloor;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isManageMyTopic() {
        return manageMyTopic;
    }

    public void setManageMyTopic(boolean manageMyTopic) {
        this.manageMyTopic = manageMyTopic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
