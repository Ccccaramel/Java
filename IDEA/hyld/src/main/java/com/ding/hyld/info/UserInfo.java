package com.ding.hyld.info;

import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.mapper.DictionaryMapper;
import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;

public class UserInfo {
    private Integer id;
    /**
     * 用于登录,既可能是id也可能是name(昵称)
     */
    private String account;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;
    private String name;
    private String qq;

    private Dictionary type;
    private Dictionary status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        setCreateTimeStr(TimeUtils.toString(this.createTime));
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
    }

    public Dictionary getStatus() {
        return status;
    }

    public void setStatus(Dictionary status) {
        this.status = status;
    }
}
