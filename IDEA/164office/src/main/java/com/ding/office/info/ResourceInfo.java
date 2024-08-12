package com.ding.office.info;

import com.ding.office.entity.Dictionary;
import com.ding.office.utils.ResourcesPathUtils;
import com.ding.office.utils.TimeUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public class ResourceInfo {
    private Integer id;
    private String alias;
    private String fileName;
    private String fileNameUrl;
    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    private Dictionary type;

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        if(StringUtils.hasText(fileName)){
            setFileNameUrl(ResourcesPathUtils.getPhotoPath(ResourcesPathUtils.HYLD) + fileName);
        }
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

    public String getFileNameUrl() {
        return fileNameUrl;
    }

    public void setFileNameUrl(String fileNameUrl) {
        this.fileNameUrl = fileNameUrl;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "id=" + id +
                ", name='" + alias + '\'' +
                ", image='" + fileName + '\'' +
                ", imageUrl='" + fileNameUrl + '\'' +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
