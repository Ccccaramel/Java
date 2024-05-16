package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.Relationship;
import com.ding.office.info.RelationshipInfo;
import com.ding.office.mapper.RelationshipMapper;
import com.ding.office.service.RelationshipService;
import com.ding.office.vo.RelationshipVo;
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
