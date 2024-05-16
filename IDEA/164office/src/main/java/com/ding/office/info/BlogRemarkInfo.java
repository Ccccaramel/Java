package com.ding.office.info;

import com.ding.office.entity.Dictionary;
import com.ding.office.utils.TimeUtils;
import com.ding.office.utils.Tree;

import java.time.LocalDateTime;
import java.util.List;

public class BlogRemarkInfo extends Tree {

    private Integer id;
    private Integer blog;
    private String remark;
    private UserInfo user;
    private UserInfo replyOfUser; // 被回复的用户
    private Dictionary status;
    private Integer rootId;
    private Integer parentId;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;
    private String address;

    private List<BlogRemarkInfo> child;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public Integer getBlog() {
        return blog;
    }

    public void setBlog(Integer blog) {
        this.blog = blog;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        setCreateTimeStr(TimeUtils.toString(createTime,TimeUtils.FORMAT_1));
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Dictionary getStatus() {
        return status;
    }

    public void setStatus(Dictionary status) {
        this.status = status;
    }

    public UserInfo getReplyOfUser() {
        return replyOfUser;
    }

    public void setReplyOfUser(UserInfo replyOfUser) {
        this.replyOfUser = replyOfUser;
    }

    public List<BlogRemarkInfo> getChild() {
        return child;
    }

    public void setChild(List<BlogRemarkInfo> child) {
        this.child = child;
    }
}
