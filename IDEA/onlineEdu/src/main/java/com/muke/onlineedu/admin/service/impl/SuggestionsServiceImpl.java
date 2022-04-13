package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.SuggestionsDao;
import com.muke.onlineedu.admin.entity.Suggestions;
import com.muke.onlineedu.admin.service.SuggestionsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("suggestionsService")
public class SuggestionsServiceImpl extends ServiceImpl<SuggestionsDao, Suggestions> implements SuggestionsService {
    @Override
    public void addSuggestions(int userId, String text) {
        baseMapper.addSuggestions(userId,text);
    }

    @Override
    public int getSuggestionsTableRow() {
        return baseMapper.findAll().size();
    }

    @Override
    public List<Suggestions> getPartSuggestions(int startPage, int pageSize) {
        return baseMapper.getPartSuggestions(startPage, pageSize);
    }
}
