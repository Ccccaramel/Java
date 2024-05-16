package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

import java.time.LocalDateTime;

/**
 * 随身妙具
 */
@TableName("gadget")
public class Gadget extends BaseObject {
    private Integer gameRole;
    private String name;
    private String details;
    private String image;
    private LocalDateTime launchTime;
    private Integer duraMax;

    public Integer getGameRole() {
        return gameRole;
    }

    public void setGameRole(Integer gameRole) {
        this.gameRole = gameRole;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public Integer getDuraMax() {
        return duraMax;
    }

    public void setDuraMax(Integer duraMax) {
        this.duraMax = duraMax;
    }
}
