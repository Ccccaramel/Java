package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.hyld.entity.base.BaseObject;
import com.ding.hyld.utils.ResourcesPathUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@TableName("topic")
public class Topic extends BaseObject {
    private String rubric;
    private String text;
    private Integer parentId;
    private Integer userId;
    private Integer floor;
    private Integer topicId;
    private Integer status;
    private String ip;
    private String address;
    private Integer belongToFloor;
    private List<String> images = new ArrayList<>();

    public List<String> getImages() {
        return images;
    }

    public void setImages(String images) {
        if(StringUtils.hasText(images)){
            for(String image:images.split(";")){
                if(StringUtils.hasText(image)){
                    this.images.add(ResourcesPathUtils.getPhotoPath() + image);
                }
            }
        }
    }

    public String getRubric() {
        return rubric;
    }

    public void setRubric(String rubric) {
        this.rubric = rubric;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
