package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("user_results")
public class UserResults implements Serializable {
    private String answerSheetId;
    private int intuserId;
    private int intquestionId;
    private String userKey;
    private int userScore;

    public String getAnswerSheetId() {
        return answerSheetId;
    }

    public void setAnswerSheetId(String answerSheetId) {
        this.answerSheetId = answerSheetId;
    }

    public int getIntuserId() {
        return intuserId;
    }

    public void setIntuserId(int intuserId) {
        this.intuserId = intuserId;
    }

    public int getIntquestionId() {
        return intquestionId;
    }

    public void setIntquestionId(int intquestionId) {
        this.intquestionId = intquestionId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
}
