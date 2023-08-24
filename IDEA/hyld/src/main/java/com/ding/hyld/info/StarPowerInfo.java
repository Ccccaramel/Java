package com.ding.hyld.info;

import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.utils.TimeUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public class StarPowerInfo {
    private GameRoleInfo gameRole;
    private String name;
    private String details;
    private Integer id;
    private String image;
    private String imageUrl;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;
    private String launchTime;
    private String launchTimeStr;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        if(StringUtils.hasText(image)){
            setImageUrl(ResourcesPathUtils.getPhotoPath(ResourcesPathUtils.HYLD) +image);
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public GameRoleInfo getGameRole() {
        return gameRole;
    }

    public void setGameRole(GameRoleInfo gameRole) {
        this.gameRole = gameRole;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        this.setCreateTimeStr(TimeUtils.toString(createTime,TimeUtils.FORMAT_1));
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime.toString();
        this.setLaunchTimeStr(TimeUtils.toString(launchTime,TimeUtils.FORMAT_1));
    }

    public String getLaunchTimeStr() {
        return launchTimeStr;
    }

    public void setLaunchTimeStr(String launchTimeStr) {
        this.launchTimeStr = launchTimeStr;
    }

    @Override
    public String toString() {
        return "StarPowerInfo{" +
                "gameRole=" + gameRole +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", id=" + id +
                ", image='" + image + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                ", launchTime='" + launchTime + '\'' +
                ", launchTimeStr='" + launchTimeStr + '\'' +
                '}';
    }
}
