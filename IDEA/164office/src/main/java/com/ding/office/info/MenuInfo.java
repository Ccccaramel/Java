package com.ding.office.info;

import com.ding.office.entity.Dictionary;
import com.ding.office.utils.Tree;

import java.time.LocalDateTime;

public class MenuInfo extends Tree {
    private String name;
    private LocalDateTime createTime;
    private Dictionary type;
    private String note;
    private String mark;
    private boolean have; // 是否拥有此功能

    public MenuInfo() {
        super();
    }

    public MenuInfo(int id, int parentId) {
        super(id, parentId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public boolean isHave() {
        return have;
    }

    public void setHave(boolean have) {
        this.have = have;
    }

    @Override
    public String toString() {
        return "MenuInfo{" +
                "name='" + name + '\'' +
                ", createTime=" + createTime +
                ", type=" + type +
                ", note='" + note + '\'' +
                ", mark='" + mark + '\'' +
                ", have=" + have +
                '}';
    }
}
