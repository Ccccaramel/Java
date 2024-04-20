package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Relationship;
import com.ding.hyld.info.RelationshipInfo;
import com.ding.hyld.mapper.RelationshipMapper;
import com.ding.hyld.service.RelationshipService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.RelationshipVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServiceImpl extends ServiceImpl<RelationshipMapper, Relationship> implements RelationshipService {


    @Override
    public List<RelationshipInfo> searchRelationship(RelationshipVo relationshipVo) {
        return baseMapper.searchRelationship(relationshipVo);
    }

    @Override
    public void add(RelationshipVo relationshipVo) {
        baseMapper.add(relationshipVo);
    }


    @Override
    public void update(RelationshipVo relationshipVo) {
        baseMapper.update(relationshipVo);
    }
}
