package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.info.DictionaryInfo;
import com.ding.hyld.mapper.DictionaryMapper;
import com.ding.hyld.service.DictionaryService;
import com.ding.hyld.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    /**
     * 查询指定类型所有种类
     * @param teamMemberStatus
     * @param excludeTypeValueList 排除的种类,例如踢人更改队员状态时,”正常队员“不可出现在下拉框中
     * @return
     */
    @Override
    public List<Dictionary> findByType(String teamMemberStatus,List<Integer> excludeTypeValueList) {
        return baseMapper.findByType(teamMemberStatus, excludeTypeValueList);
    }

    @Override
    public List<Dictionary> searchDictionary(Page page, DictionaryInfo dictionaryInfo) {
        return baseMapper.searchDictionary(page, dictionaryInfo);
    }

    @Override
    public void addDictionaryInfo(DictionaryInfo dictionaryInfo) {
        baseMapper.addDictionaryInfo(dictionaryInfo);
    }

    @Override
    public void updateDictionaryInfo(DictionaryInfo dictionaryInfo) {
        baseMapper.updateDictionaryInfo(dictionaryInfo);
    }

    @Override
    public void delete(Integer dictionaryId) {
        baseMapper.deleteById(dictionaryId);
    }
}
