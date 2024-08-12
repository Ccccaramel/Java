package com.ding.office.vo;

import com.ding.office.vo.base.BaseVo;

import java.time.LocalDateTime;

public class BlogCollectionVo extends BaseVo {
    private Integer user;
    private Integer blog;
    private Boolean collection;

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getBlog() {
        return blog;
    }

    public void setBlog(Integer blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "BlogCollectionVo{" +
                "user=" + user +
                ", blog=" + blog +
                ", collection=" + collection +
                '}';
    }

    public Boolean getCollection() {
        return collection;
    }

    public void setCollection(Boolean collection) {
        this.collection = collection;
    }

}
