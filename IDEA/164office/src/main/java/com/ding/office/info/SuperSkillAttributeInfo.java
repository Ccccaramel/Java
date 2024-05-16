package com.ding.office.info;

import com.ding.office.utils.TimeUtils;

import java.time.LocalDateTime;

public class SuperSkillAttributeInfo {
    private SuperSkillInfo superSkill;
    private String name;
    private String details;
    private Integer id;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    public SuperSkillInfo getSuperSkill() {
        return superSkill;
    }

    public void setSuperSkill(SuperSkillInfo superSkill) {
        this.superSkill = superSkill;
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

    @Override
    public String toString() {
        return "SuperSkillAttributeInfo{" +
                "superSkill=" + superSkill +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", id=" + id +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
