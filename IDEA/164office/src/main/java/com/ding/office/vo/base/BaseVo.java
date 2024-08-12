package com.ding.office.vo.base;

public class BaseVo {

    private Integer id;
    private String note;

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

    @Override
    public String toString() {
        return "BaseVo{" +
                "id=" + id +
                ", note='" + note + '\'' +
                '}';
    }
}
