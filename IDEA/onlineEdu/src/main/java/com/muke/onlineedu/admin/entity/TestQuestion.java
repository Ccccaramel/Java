package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@TableName("test_question")
public class TestQuestion implements Serializable {
    private int testId;
    private int questionId;
    private int questionNumber;
    private String questionContent;
    private String rightKey;
    private String analysis;
    private int score;

    private List<TestOptions> testOptions;
    private String userKey;
    private boolean userIsRight;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getRightKey() {
        return rightKey;
    }

    public void setRightKey(String rightKey) {
        this.rightKey = rightKey;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<TestOptions> getTestOptions() {
        return testOptions;
    }

    public void setTestOptions(List<TestOptions> testOptions) {
        this.testOptions = testOptions;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public boolean getUserIsRight() {
        return userIsRight;
    }

    public void setUserIsRight(boolean userIsRight) {
        this.userIsRight = userIsRight;
    }
}
