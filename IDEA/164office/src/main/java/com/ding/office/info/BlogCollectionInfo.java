package com.ding.office.info;

import java.time.LocalDateTime;

public class BlogCollectionInfo {
    private Integer id;
    private Integer blog;

    private Integer user;

    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlog() {
        return blog;
    }

    public void setBlog(Integer blog) {
        this.blog = blog;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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
}
