package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

@TableName("game_role_comment")
public class GameRoleComment extends BaseObject {
    private String text;
    private Integer parentId;
    private Integer userId;
    private Integer floor;
    private Integer gameRole;
    private Integer status;
    private String ip;
    private String address;
    private Integer belongToFloor;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBelongToFloor() {
        return belongToFloor;
    }

    public void setBelongToFloor(Integer belongToFloor) {
        this.belongToFloor = belongToFloor;
    }

    public Integer getGameRole() {
        return gameRole;
    }

    public void setGameRole(Integer gameRole) {
        this.gameRole = gameRole;
    }
}
