package com.ding.hyld.info;

import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;
import java.util.List;

public class SuperSkillInfo {
    private GameRoleInfo gameRole;
    private String name;
    private String details;
    private Integer id;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    private List<SuperSkillAttributeInfo> superSkillAttributeInfoList;

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

    public List<SuperSkillAttributeInfo> getSuperSkillAttributeInfoList() {
        return superSkillAttributeInfoList;
    }

    public void setSuperSkillAttributeInfoList(List<SuperSkillAttributeInfo> superSkillAttributeInfoList) {
        this.superSkillAttributeInfoList = superSkillAttributeInfoList;
    }

    @Override
    public String toString() {
        return "SuperSkillInfo{" +
                "gameRole=" + gameRole +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", id=" + id +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                ", superSkillAttributeInfoList=" + superSkillAttributeInfoList +
                '}';
    }
}
