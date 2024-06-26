package com.ding.office.info;

import com.ding.office.utils.TimeUtils;

import java.time.LocalDateTime;

public class NormalAttackAttributeInfo {
    private NormalAttackInfo normalAttack;
    private String name;
    private String details;
    private Integer id;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    public NormalAttackInfo getNormalAttack() {
        return normalAttack;
    }

    public void setNormalAttack(NormalAttackInfo normalAttack) {
        this.normalAttack = normalAttack;
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
        return "NormalAttackAttributeInfo{" +
                "normalAttack=" + normalAttack +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", id=" + id +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
