package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Relationship;
import com.ding.office.info.RelationshipInfo;
import com.ding.office.vo.RelationshipVo;
import java.util.List;

public interface RelationshipService extends IService<Relationship> {
    List<RelationshipInfo> searchRelationship(RelationshipVo relationshipVo);

    void add(RelationshipVo relationshipVo);

    void update(RelationshipVo relationshipVo);
}
