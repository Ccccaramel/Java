package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.Suggestions;

import java.util.List;

public interface SuggestionsService extends IService<Suggestions> {
    void addSuggestions(int userId, String text);

    int getSuggestionsTableRow();

    List<Suggestions> getPartSuggestions(int startPage, int pageSize);
}
