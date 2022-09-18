package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.info.DictionaryInfo;
import com.ding.hyld.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictionaryMapper extends BaseMapper<Dictionary> {
    List<Dictionary> findByType(String teamMemberStatus,List<Integer> excludeTypeValueList);

    Dictionary findById(Integer id);

    List<Dictionary> searchDictionary(@Param("page") Page page,@Param("dictionaryInfo") DictionaryInfo dictionaryInfo);

    void addDictionaryInfo(@Param("dictionaryInfo") DictionaryInfo dictionaryInfo);

    void updateDictionaryInfo(@Param("dictionaryInfo") DictionaryInfo dictionaryInfo);

    void deleteById(Integer dictionaryId);
}
