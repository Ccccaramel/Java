package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.TestOptions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestOptionsDao extends BaseMapper<TestOptions> {
    void saveTestOption(int questionNumber, int optionNumber, String optionContent);
    void delTestOption(int questionId);
    List<TestOptions> getTestOption(int questionId);
}
