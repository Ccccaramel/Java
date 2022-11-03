package com.ding.hyld.vo;

public class SuperSkillVo {
    private Integer gameRoleId;
    private Integer gameRoleName;
    private String name;
    private String details;
    private Integer id;
    private String note;
    private boolean add;

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {

        this.add = add;
    }

    public Integer getGameRoleName() {
        return gameRoleName;
    }

    public void setGameRoleName(Integer gameRoleName) {
        this.gameRoleName = gameRoleName;
    }

    public Integer getGameRoleId() {
        return gameRoleId;
    }

    public void setGameRoleId(Integer gameRoleId) {
        this.gameRoleId = gameRoleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return details;
    }

    public void setDescribe(String describe) {
        this.details = describe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
