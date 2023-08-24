package com.ding.hyld.vo;

import java.time.LocalDateTime;

public class BillVo {
    private boolean isAdd;
    private Integer id;
    private Integer user;
    private String name;
    private Double value;
    private String detail;
    private Integer currencyType;
    private Integer type;
    private Integer status;
    private Integer weight;
    private String note;
    private LocalDateTime expenseTime;
    private String startDate;
    private String endDate;

    // 用于查询
    private boolean orderByExpenseTime; // 通过 支付时间(年月) 排序
    private boolean orderByTotalValue; // 通过 月总消费金额 排序
    private Boolean desc; // 升序

    public Boolean getDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }

    public boolean isOrderByExpenseTime() {
        return orderByExpenseTime;
    }

    public void setOrderByExpenseTime(boolean orderByExpenseTime) {
        this.orderByExpenseTime = orderByExpenseTime;
    }

    public boolean isOrderByTotalValue() {
        return orderByTotalValue;
    }

    public void setOrderByTotalValue(boolean orderByTotalValue) {
        this.orderByTotalValue = orderByTotalValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Integer currencyType) {
        this.currencyType = currencyType;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getExpenseTime() {
        return expenseTime;
    }

    public void setExpenseTime(LocalDateTime expenseTime) {
        this.expenseTime = expenseTime;
    }
}
