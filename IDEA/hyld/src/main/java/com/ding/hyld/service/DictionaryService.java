package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.info.DictionaryInfo;
import com.ding.hyld.vo.Page;

import java.util.Collection;
import java.util.List;

public interface DictionaryService extends IService<Dictionary> {
    List<Dictionary> findByType(String teamMemberStatus,List<Integer> excludeTypeValueList);

    List<Dictionary>  searchDictionary(Page page, DictionaryInfo dictionaryInfo);

    void addDictionaryInfo(DictionaryInfo dictionaryInfo);

    void updateDictionaryInfo(DictionaryInfo dictionaryInfo);

    void delete(Integer dictionaryId);

    Integer searchDictionaryOfPage(DictionaryInfo dictionaryInfo);
}
