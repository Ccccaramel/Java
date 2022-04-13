package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
@TableName("course_structure")
public class CourseStructure implements Serializable {
    private int courseId;
    private int chapterId;
    private String chapterName;
    private int sectionId;
    private String sectionName;
    private String mvAdd;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getMvAdd() {
        return mvAdd;
    }

    public void setMvAdd(String mvAdd) {
        this.mvAdd = mvAdd;
    }
}
