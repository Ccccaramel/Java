package com.ding.hyld.info;

import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.utils.TimeUtils;
import io.netty.util.internal.StringUtil;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 强化装备
 */
public class GearInfo {
    private Integer id;
    private String note;
    private String name;
    private String details;
    private String oneLevelImg;
    private String oneLevelImgUrl;
    private String twoLevelImg;
    private String twoLevelImgUrl;
    private String threeLevelImg;
    private String threeLevelImgUrl;
    private String launchTime;
    private String launchTimeStr;
    private String createTime;
    private String createTimeStr;
    private Dictionary rarity;

    public Dictionary getRarity() {
        return rarity;
    }

    public void setRarity(Dictionary rarity) {
        this.rarity = rarity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime.toString();
        setLaunchTimeStr(TimeUtils.toString(launchTime,TimeUtils.FORMAT_1));

    }

    public String getOneLevelImg() {
        return oneLevelImg;
    }

    public void setOneLevelImg(String oneLevelImg) {
        this.oneLevelImg = oneLevelImg;
        if(StringUtils.hasText(oneLevelImg)){
            setOneLevelImgUrl(ResourcesPathUtils.getPhotoPath(ResourcesPathUtils.HYLD)+oneLevelImg);
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

    public String getLaunchTimeStr() {
        return launchTimeStr;
    }

    public void setLaunchTimeStr(String launchTimeStr) {
        this.launchTimeStr = launchTimeStr;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime.toString();
        setCreateTimeStr(TimeUtils.toString(createTime,TimeUtils.FORMAT_1));
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    @Override
    public String toString() {
        return "GearInfo{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", oneLevelImg='" + oneLevelImg + '\'' +
                ", oneLevelImgUrl='" + oneLevelImgUrl + '\'' +
                ", twoLevelImg='" + twoLevelImg + '\'' +
                ", twoLevelImgUrl='" + twoLevelImgUrl + '\'' +
                ", threeLevelImg='" + threeLevelImg + '\'' +
                ", threeLevelImgUrl='" + threeLevelImgUrl + '\'' +
                ", launchTime='" + launchTime + '\'' +
                ", launchTimeStr='" + launchTimeStr + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", rarity=" + rarity +
                '}';
    }
}
