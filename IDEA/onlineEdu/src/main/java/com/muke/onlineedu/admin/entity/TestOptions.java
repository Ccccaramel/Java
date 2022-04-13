package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("test_options")
public class TestOptions implements Serializable {
    private int questionId;
    private int questionOption;
    private String optionContent;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(int questionOption) {
        this.questionOption = questionOption;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }
}
