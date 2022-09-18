package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.hyld.entity.base.BaseObject;

@TableName("user")
public class User extends BaseObject {
    private String name;
    private Integer status;
    private Integer type;
    private String qq;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
