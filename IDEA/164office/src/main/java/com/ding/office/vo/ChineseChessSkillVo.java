package com.ding.office.vo;

public class ChineseChessSkillVo {
    private Integer id;
    private String name;
    private String details;

    public ChineseChessSkillVo() {
    }

    public ChineseChessSkillVo(Integer id, String name, String details) {
        this.id = id;
        this.name = name;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
