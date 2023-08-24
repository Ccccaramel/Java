package com.ding.hyld.info;

import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;

public class BillInfo {
    private Integer id;

    private UserInfo user;
    private String name;
    private Double value;
    private String detail;
    private Dictionary currencyType;
    private Dictionary type;
    private Dictionary status;
    private Dictionary weight;
    private String expenseTime;
    private String expenseTimeStr;

    private String note;
    private LocalDateTime createTime;

    public Dictionary getStatus() {
        return status;
    }

    public void setStatus(Dictionary status) {
        this.status = status;
    }

    public Dictionary getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Dictionary currencyType) {
        this.currencyType = currencyType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
    }

    public Dictionary getWeight() {
        return weight;
    }

    public void setWeight(Dictionary weight) {
        this.weight = weight;
    }

    public String getExpenseTime() {
        return expenseTime;
    }

    public void setExpenseTime(LocalDateTime expenseTime) {
        this.expenseTime = expenseTime.toString();
        setExpenseTimeStr(TimeUtils.toString(expenseTime,TimeUtils.FORMAT_1));
    }

    public String getExpenseTimeStr() {
        return expenseTimeStr;
    }

    public void setExpenseTimeStr(String expenseTimeStr) {
        this.expenseTimeStr = expenseTimeStr;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
