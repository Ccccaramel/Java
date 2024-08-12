package com.ding.office.info;

import com.ding.office.entity.Dictionary;
import com.ding.office.utils.CommonUtils;
import com.ding.office.utils.TimeUtils;

import java.time.LocalDateTime;
import java.util.Map;

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
    private ResourceInfo headPortrait; // 头像
    private Integer ex; // 经验

    private RoleInfo role;
    private Dictionary status;

    private Integer grade;
    private Integer exEx;
    private Integer currentLvMaxEx;
    private Integer proportion;
    private String no; // 指纹
    private String email;
    private int coin;

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getExEx() {
        return exEx;
    }

    public void setExEx(Integer exEx) {
        this.exEx = exEx;

    }

    public Integer getCurrentLvMaxEx() {
        return currentLvMaxEx;
    }

    public void setCurrentLvMaxEx(Integer currentLvMaxEx) {
        this.currentLvMaxEx = currentLvMaxEx;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        setCreateTimeStr(TimeUtils.toString(this.createTime,TimeUtils.FORMAT_1));
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

    public RoleInfo getRole() {
        return role;
    }

    public void setRole(RoleInfo role) {
        this.role = role;
    }

    public Dictionary getStatus() {
        return status;
    }

    public void setStatus(Dictionary status) {
        this.status = status;
    }

    public ResourceInfo getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(ResourceInfo headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getEx() {
        return ex;
    }

    public void setEx(Integer ex) {
        this.ex = ex;
        Integer lv = 1,val = 0;
        Map<String, Integer> dataMap = CommonUtils.exToLvTools(lv, val, ex);
        setGrade(dataMap.get("grade"));
        setExEx(dataMap.get("exEx"));
        setCurrentLvMaxEx(dataMap.get("currentLvMaxEx"));
        setProportion(dataMap.get("proportion"));
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                ", name='" + name + '\'' +
                ", qq='" + qq + '\'' +
                ", image=" + headPortrait +
                ", ex=" + ex +
                ", role=" + role +
                ", status=" + status +
                ", grade=" + grade +
                ", exEx=" + exEx +
                ", currentLvMaxEx=" + currentLvMaxEx +
                ", proportion=" + proportion +
                ", no='" + no + '\'' +
                ", email='" + email + '\'' +
                ", coin='" + coin + '\'' +
                '}';
    }
}
