package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.muke.onlineedu.common.entity.GMPower;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@TableName("administrator")
public class Admin implements Serializable {
    private Integer gmId;
    private String gmEmail;
    private String gmTel;
    private String gmPassword;
    private Integer gmPower;
    private Integer gmAccountStatus;

    @TableField(exist = false)
    private String gmPowerClass; // 权限级别
    @TableField(exist = false)
    private String accountStatus; // 账户状态
    @TableField(exist = false)
    private String gmInitPassword; // 初始密码

    public Integer getGmId() {
        return gmId;
    }

    public void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    public String getGmEmail() {
        return gmEmail;
    }

    public void setGmEmail(String gmEmail) {
        this.gmEmail = gmEmail;
    }

    public String getGmTel() {
        return gmTel;
    }

    public void setGmTel(String gmTel) {
        this.gmTel = gmTel;
    }

    public String getGmPassword() {
        return gmPassword;
    }

    public void setGmPassword(String gmPassword) {
        this.gmPassword = gmPassword;
    }

    public Integer getGmPower() {
        return gmPower;
    }

    public void setGmPower(Integer gmPower) {
        this.gmPower = gmPower;
    }

    public Integer getGmAccountStatus() {
        return gmAccountStatus;
    }

    public void setGmAccountStatus(Integer gmAccountStatus) {
        this.gmAccountStatus = gmAccountStatus;
    }

    public String getGmPowerClass() {
        return gmPowerClass;
    }

    public void setGmPowerClass(String gmPowerClass) {
        this.gmPowerClass = gmPowerClass;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getGmInitPassword() {
        return gmInitPassword;
    }

    public void setGmInitPassword(String gmInitPassword) {
        this.gmInitPassword = gmInitPassword;
    }
}
