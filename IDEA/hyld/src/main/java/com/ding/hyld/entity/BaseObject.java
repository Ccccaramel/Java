package com.ding.hyld.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseObject implements Serializable {
    private Integer id;
    private LocalDateTime createTime;
    private String note;

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
    }

    public String getNote() {
        return note;
    }

    public void setNote(String teamNote) {
        this.note = teamNote;
    }
}
