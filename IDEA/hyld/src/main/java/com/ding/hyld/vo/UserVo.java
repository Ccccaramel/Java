package com.ding.hyld.vo;

import java.time.LocalDateTime;

public class UserVo {
    private Integer id;
    /**
     * 用于登录,既可能是id也可能是name(昵称)
     */
    private String account;
    private String name;
    private String password;
    private String qq;
    private Integer status;
    private Integer role;
    private String note;
    private String no; // 指纹
    private String fingerprint; // 指纹
    private Integer headPortraitId; // 修改头像
    private LocalDateTime time; // 今日起始时间

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getHeadPortraitId() {
        return headPortraitId;
    }

    public void setHeadPortraitId(Integer headPortraitId) {
        this.headPortraitId = headPortraitId;
    }
}
