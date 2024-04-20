package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Relationship;
import com.ding.hyld.info.RelationshipInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.RelationshipVo;
import java.util.List;

public interface RelationshipService extends IService<Relationship> {
    List<RelationshipInfo> searchRelationship(RelationshipVo relationshipVo);

    void add(RelationshipVo relationshipVo);

    void update(RelationshipVo relationshipVo);
}
