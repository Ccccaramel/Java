package com.ding.office.info;

import com.alibaba.fastjson2.annotation.JSONField;
import com.ding.office.utils.TimeUtils;

import java.time.LocalDateTime;

public class UpdateLogInfo {
    private Integer id;
    private String text;
    @JSONField(format = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    /**
     * jasypt 加密解密,解密需要 JSON 格式,方便转换为对象
     * @return
     */
    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", text:'" + text + '\'' +
                ", createTime:'" + createTime +
                "', createTimeStr:'" + createTimeStr + '\'' +
                ", note:'" + note + '\'' +
                '}';
    }
}
