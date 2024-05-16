package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

@TableName("super_skill_attribute")
public class SuperSkillAttribute extends BaseObject {
    private Integer superSkill;
    private String name;
    private String details;

    public Integer getSuperSkill() {
        return superSkill;
    }

    public void setSuperSkill(Integer superSkill) {
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
}
