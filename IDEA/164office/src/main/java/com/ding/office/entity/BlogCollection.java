package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.ding.office.entity.base.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@TableName("blog_collection")
public class BlogCollection extends BaseObject {
    private LocalDateTime createTime;
    private Integer blog;
    private Integer user;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getBlog() {
        return blog;
    }

    public void setBlog(Integer blog) {
        this.blog = blog;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BlogCollection{" +
                "createTime=" + createTime +
                ", blog=" + blog +
                ", user=" + user +
                '}';
    }
}
