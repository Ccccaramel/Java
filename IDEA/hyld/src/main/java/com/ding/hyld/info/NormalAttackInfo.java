package com.ding.hyld.info;

import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;
import java.util.List;

public class NormalAttackInfo {
    private GameRoleInfo gameRole;
    private String name;
    private String details;
    private Integer id;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    private List<NormalAttackAttributeInfo> normalAttackAttributeInfoList;

    public GameRoleInfo getGameRole() {
        return gameRole;
    }

    public void setGameRole(GameRoleInfo gameRole) {
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
        this.setCreateTimeStr(TimeUtils.toString(createTime,TimeUtils.FORMAT_1));
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

    public List<NormalAttackAttributeInfo> getNormalAttackAttributeInfoList() {
        return normalAttackAttributeInfoList;
    }

    public void setNormalAttackAttributeInfoList(List<NormalAttackAttributeInfo> normalAttackAttributeInfoList) {
        this.normalAttackAttributeInfoList = normalAttackAttributeInfoList;
    }
}
