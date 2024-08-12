package com.ding.office.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.office.entity.base.BaseObject;

@TableName("super_bag")
public class SuperBag extends BaseObject {
    private String name;
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
