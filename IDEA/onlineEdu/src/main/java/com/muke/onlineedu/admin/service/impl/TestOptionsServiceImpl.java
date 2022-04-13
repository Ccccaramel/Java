package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.TestOptionsDao;
import com.muke.onlineedu.admin.entity.TestOptions;
import com.muke.onlineedu.admin.entity.TestQuestion;
import com.muke.onlineedu.admin.service.TestOptionsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("testOptionsService")
public class TestOptionsServiceImpl extends ServiceImpl<TestOptionsDao, TestOptions> implements TestOptionsService {
    @Override
    public void saveTestOption(int questionNumber, int optionNumber, String optionContent) {
        baseMapper.saveTestOption(questionNumber,optionNumber,optionContent);
    }

    @Override
    public void delTestOption(List<Integer> questionIdList) {
        for(Integer questionId:questionIdList){
            baseMapper.delTestOption(questionId);
        }
    }

    @Override
    public List<TestOptions> getTestOption(List<TestQuestion> testQuestionList) {
        List<TestOptions> testOptionsList=new ArrayList<>();
        for(TestQuestion testQuestion:testQuestionList){
            testOptionsList.addAll(baseMapper.getTestOption(testQuestion.getQuestionId()));
        }
        return testOptionsList;
    }
}
