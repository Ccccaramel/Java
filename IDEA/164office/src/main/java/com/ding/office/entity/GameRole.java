package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

import java.time.LocalDateTime;

@TableName("game_role")
public class GameRole extends BaseObject {
    private String name;
    private String headImg; // 头像
    private String portrait; // 肖像
    private Integer rarity; // 稀有度
    private Integer position; // 定位
    private String profile; // 角色简介
    private LocalDateTime launchTime; // 上线时间
    private String hp; // 生命值
    private String speed; // 移速
    private String inborn; // 天赋
    private Integer basicForm; // 基础形态
    private Integer sequence; // 顺序(非基础形态则需要对其排序)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public LocalDateTime getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getInborn() {
        return inborn;
    }

    public void setInborn(String inborn) {
        this.inborn = inborn;
    }

    public Integer getBasicForm() {
        return basicForm;
    }

    public void setBasicForm(Integer basicForm) {
        this.basicForm = basicForm;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
