package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.TestOptions;
import com.muke.onlineedu.admin.entity.TestQuestion;

import java.util.List;

public interface TestOptionsService extends IService<TestOptions> {
    void saveTestOption(int questionNumber, int optionNumber, String optionContent);
    void delTestOption(List<Integer> questionIdList);

    List<TestOptions> getTestOption(List<TestQuestion> testQuestionList);
}
