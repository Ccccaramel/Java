package com.ding.office.info;

import com.ding.office.utils.TimeUtils;

import java.time.LocalDateTime;

public class OfficialVersionUpdateLogInfo{
    private Integer id;
    private String createTime;
    private String createTimeStr;
    private String note;
    private String name;
    private String updateTime;
    private String updateTimeStr;
    private String details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime.toString();
        setUpdateTimeStr(TimeUtils.toString(updateTime,TimeUtils.FORMAT_1));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "OfficialVersionUpdateLogInfo{" +
                "id=" + id +
                ", createTime='" + createTime + '\'' +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                ", name='" + name + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
