package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.muke.onlineedu.common.entity.Evaluate;

import java.io.Serializable;
import java.util.Date;

@TableName("user_achievement")
public class UserAchievement implements Serializable {
    private int userId;
    private String answerSheetId;
    private int courseId;
    private int testId;
    private int userAchievement;
    private Date submitTestDate;
    private int effectiveness;
    private int totalScore;
    private int pass;

    private Evaluate evaluate;
    private Course course;
    private ItemBack itemBack;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAnswerSheetId() {
        return answerSheetId;
    }

    public void setAnswerSheetId(String answerSheetId) {
        this.answerSheetId = answerSheetId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getUserAchievement() {
        return userAchievement;
    }

    public void setUserAchievement(int userAchievement) {
        this.userAchievement = userAchievement;
    }

    public Date getSubmitTestDate() {
        return submitTestDate;
    }

    public void setSubmitTestDate(Date submitTestDate) {
        this.submitTestDate = submitTestDate;
    }

    public int getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(int effectiveness) {
        this.effectiveness = effectiveness;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public Evaluate getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Evaluate evaluate) {
        this.evaluate = evaluate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ItemBack getItemBack() {
        return itemBack;
    }

    public void setItemBack(ItemBack itemBack) {
        this.itemBack = itemBack;
    }
}
