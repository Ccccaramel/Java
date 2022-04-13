package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.UserAchievementDao;
import com.muke.onlineedu.admin.entity.UserAchievement;
import com.muke.onlineedu.admin.service.TestQuestionService;
import com.muke.onlineedu.admin.service.UserAchievementService;
import com.muke.onlineedu.admin.service.UserResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service("userAchievementService")
public class UserAchievementServiceImpl extends ServiceImpl<UserAchievementDao, UserAchievement> implements UserAchievementService {
    @Autowired
    TestQuestionService testQuestionService;
    @Autowired
    UserResultsService userResultsService;
    @Override
    public void saveUserTest(Map<String, String> data, int userId) {
        String answerSheetId=this.getAnswerSheetId();
        int usertotalScore=0;
        int testId= Integer.parseInt(data.get("testId"));
        int courseId=Integer.parseInt(data.get("courseId"));
        int totalScore=Integer.parseInt(data.get("hTotalScore"));
        int passLine=Integer.parseInt(data.get("hPassLine"));
        int pass=1000;
        for(int i=1;;i++) {
            if( data.get("problemId-"+i)==null) {
                break;
            }else {
                int problemId=Integer.parseInt(data.get("problemId-"+i));
                String userKey=data.get("question-"+i+"-user-key");
                int score=testQuestionService.revise(problemId, userKey);
                usertotalScore+=score;
                userResultsService.saveAnswerSheet(answerSheetId, userId, problemId, userKey, score);  //保存答题卡
            }
            if(usertotalScore>=passLine) {
                pass=1001;
            }
        }
        baseMapper.saveGrades(answerSheetId, userId, courseId, testId, usertotalScore, totalScore, pass);//保存成绩
    }

    @Override
    public int getAppointUserAchievementRow(int userId) {
        return baseMapper.findByUserId(userId).size();
    }

    @Override
    public List<UserAchievement> getPartUserAchievement(int userId, int startPage, int pageSize) {
        return baseMapper.getPartUserAchievement(userId,startPage,pageSize);
    }

    private String getAnswerSheetId() {
        DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar= Calendar.getInstance();
        int num=1000+(int)(Math.random()*(9000));
        String name=df.format(calendar.getTime());
        return name;
    }
}
