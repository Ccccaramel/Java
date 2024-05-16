package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

import java.time.LocalDateTime;

/**
 * 强化装备
 */
@TableName("gear")
public class Gear extends BaseObject {
    private String name;
    private String details;
    private String oneLevelImg;
    private String twoLevelImg;
    private String threeLevelImg;
    private LocalDateTime launchTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime;
    }

    public String getOneLevelImg() {
        return oneLevelImg;
    }

    public void setOneLevelImg(String oneLevelImg) {
        this.oneLevelImg = oneLevelImg;
    }

    public String getTwoLevelImg() {
        return twoLevelImg;
    }

    public void setTwoLevelImg(String twoLevelImg) {
        this.twoLevelImg = twoLevelImg;
    }

    public String getThreeLevelImg() {
        return threeLevelImg;
    }

    public void setThreeLevelImg(String threeLevelImg) {
        this.threeLevelImg = threeLevelImg;
    }
}
