package com.ding.office.info;

import com.ding.office.entity.Dictionary;
import com.ding.office.entity.Topic;
import com.ding.office.utils.ResourcesPathUtils;
import com.ding.office.utils.TimeUtils;
import com.ding.office.utils.Tree;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TopicInfo extends Tree {
    private String rubric;
    private String text;
    private Integer floor;
    private Integer belongToFloor;
    private Integer topicId;
    private UserInfo userInfo;
    private Dictionary status;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;
    private Integer head; // 热度/回复数量
    private UserInfo replyUser; // 回复对象
    private String address;
    private List<String> images = new ArrayList<>();

    public List<String> getImages() {
        return images;
    }

    public void setImages(String images) {
        if(StringUtils.hasText(images)){
            for(String image:images.split(";")){
                if(StringUtils.hasText(image)){
                    this.images.add(ResourcesPathUtils.getPhotoPath(ResourcesPathUtils.HYLD) + image);
                }
            }
        }
    }

    private List<TopicInfo> replyInfo;

    private Topic parentInfo;

    public Integer getBelongToFloor() {
        return belongToFloor;
    }

    public void setBelongToFloor(Integer belongToFloor) {
        this.belongToFloor = belongToFloor;
    }

    public Topic getParentInfo() {
        return parentInfo;
    }

    public void setParentInfo(Topic parentInfo) {
        this.parentInfo = parentInfo;
    }

    public String getRubric() {
        return rubric;
    }

    public void setRubric(String rubric) {
        this.rubric = rubric;
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

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
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

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public List<TopicInfo> getReplyInfo() {
        return replyInfo;
    }

    public void setReplyInfo(List<TopicInfo> replyInfo) {
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
        return "TopicInfo{" +
                "rubric='" + rubric + '\'' +
                ", text='" + text + '\'' +
                ", floor=" + floor +
                ", belongToFloor=" + belongToFloor +
                ", topicId=" + topicId +
                ", userInfo=" + userInfo +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                ", head=" + head +
                ", replyUser=" + replyUser +
                ", address='" + address + '\'' +
                ", images=" + images +
                ", replyInfo=" + replyInfo +
                ", parentInfo=" + parentInfo +
                '}';
    }
}
