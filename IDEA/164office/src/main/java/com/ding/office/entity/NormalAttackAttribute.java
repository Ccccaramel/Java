package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

@TableName("normal_attack_attribute")
public class NormalAttackAttribute extends BaseObject {
    private Integer normalAttack;
    private String name;
    private String details;

    public Integer getNormalAttack() {
        return normalAttack;
    }

    public void setNormalAttack(Integer normalAttack) {
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
}
