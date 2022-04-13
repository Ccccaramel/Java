package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.Suggestions;
import com.muke.onlineedu.admin.service.SuggestionsService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SuggestionsDao extends BaseMapper<Suggestions> {
    void addSuggestions(int userId, String text);
    List<Suggestions> findAll();
    List<Suggestions> getPartSuggestions(int startPage,int pageSize);
}
