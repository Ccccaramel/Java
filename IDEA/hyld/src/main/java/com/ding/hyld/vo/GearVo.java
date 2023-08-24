package com.ding.hyld.vo;

import com.ding.hyld.utils.ResourcesPathUtils;
import org.springframework.util.StringUtils;

/**
 * 强化装备
 */
public class GearVo {
    private Integer id;
    private String note;
    private String title;
    private String name;
    private String details;
    private String oneLevelImg;
    private String oneLevelImgUrl;
    private String twoLevelImg;
    private String twoLevelImgUrl;
    private String threeLevelImg;
    private String threeLevelImgUrl;
    private String launchTime;
    private boolean add;
    private Integer rarity; // 稀有度，不是所有装备都有该属性

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(String launchTime) {
        this.launchTime = launchTime;
    }

    public String getOneLevelImg() {
        return oneLevelImg;
    }

    public void setOneLevelImg(String oneLevelImg) {
        this.oneLevelImg = oneLevelImg;
        if(StringUtils.hasText(oneLevelImg)){
            setTwoLevelImgUrl(ResourcesPathUtils.getPhotoPath(ResourcesPathUtils.HYLD)+oneLevelImg);
        }
    }

    public String getTwoLevelImg() {
        return twoLevelImg;
    }

    public void setTwoLevelImg(String twoLevelImg) {
        this.twoLevelImg = twoLevelImg;
        if(StringUtils.hasText(twoLevelImg)){
            setTwoLevelImgUrl(ResourcesPathUtils.getPhotoPath(ResourcesPathUtils.HYLD)+twoLevelImg);
        }
    }

    public String getThreeLevelImg() {
        return threeLevelImg;
    }

    public void setThreeLevelImg(String threeLevelImg) {
        this.threeLevelImg = threeLevelImg;
        if(StringUtils.hasText(threeLevelImg)){
            setThreeLevelImgUrl(ResourcesPathUtils.getPhotoPath(ResourcesPathUtils.HYLD)+threeLevelImg);
        }
    }

    public String getOneLevelImgUrl() {
        return oneLevelImgUrl;
    }

    public void setOneLevelImgUrl(String oneLevelImgUrl) {
        this.oneLevelImgUrl = oneLevelImgUrl;
    }

    public String getTwoLevelImgUrl() {
        return twoLevelImgUrl;
    }

    public void setTwoLevelImgUrl(String twoLevelImgUrl) {
        this.twoLevelImgUrl = twoLevelImgUrl;
    }

    public String getThreeLevelImgUrl() {
        return threeLevelImgUrl;
    }

    public void setThreeLevelImgUrl(String threeLevelImgUrl) {
        this.threeLevelImgUrl = threeLevelImgUrl;
    }
}
