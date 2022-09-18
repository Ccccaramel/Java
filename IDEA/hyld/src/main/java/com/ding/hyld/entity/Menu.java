package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.hyld.entity.base.BaseObject;

@TableName("menu")
public class Menu extends BaseObject {
    private String name;
    private Integer parentId;
    private Integer type;
    private String mark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
