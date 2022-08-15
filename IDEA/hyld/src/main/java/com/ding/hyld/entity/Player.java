package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("player")
public class Player extends BaseObject {
    private String scid;
    private String name;

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
        this.scid = scid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
