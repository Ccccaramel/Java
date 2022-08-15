package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("dictionary")
public class Dictionary extends BaseObject {
    private String name;
    private Integer value;
    private String type;
    private String typeName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
