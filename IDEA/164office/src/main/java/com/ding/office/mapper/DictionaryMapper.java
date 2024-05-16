package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Dictionary;
import com.ding.office.info.DictionaryInfo;
import com.ding.office.vo.Page;
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

    Integer searchDictionaryOfPage(@Param("dictionaryInfo") DictionaryInfo dictionaryInfo);
}
