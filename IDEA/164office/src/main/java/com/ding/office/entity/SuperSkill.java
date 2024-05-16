package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

@TableName("super_skill")
public class SuperSkill extends BaseObject {
    private Integer gameRole;
    private String name;
    private String details;

    public Integer getGameRole() {
        return gameRole;
    }

    public void setGameRole(Integer gameRole) {
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
}
