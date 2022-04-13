package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.muke.onlineedu.common.entity.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@TableName("item_back")
public class ItemBack implements Serializable {
    private int testId;
    private int courseId;
    private String testName;
    private int testStatus;
    private int totalScore;
    private int passLine;
    private int teacherId;

    private Teacher teacher;
    private AccountStatus accountStatus;

    public ItemBack(int testId, int courseId, String testName, AccountStatus accountStatus) {
        this.testId = testId;
        this.courseId = courseId;
        this.testName = testName;
        this.accountStatus = accountStatus;
    }

    public ItemBack(int testId, int courseId, String testName, int testStatus, int totalScore, int passLine, int teacherId) {
        this.testId = testId;
        this.courseId = courseId;
        this.testName = testName;
        this.testStatus = testStatus;
        this.totalScore = totalScore;
        this.passLine = passLine;
        this.teacherId = teacherId;
    }

    public ItemBack(int courseId, String testName, int totalScore, int passLine, int teacherId) {
        this.courseId = courseId;
        this.testName = testName;
        this.totalScore = totalScore;
        this.passLine = passLine;
        this.teacherId = teacherId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(int testStatus) {
        this.testStatus = testStatus;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getPassLine() {
        return passLine;
    }

    public void setPassLine(int passLine) {
        this.passLine = passLine;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
