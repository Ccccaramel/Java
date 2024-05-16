package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Relationship;
import com.ding.office.info.RelationshipInfo;
import com.ding.office.vo.RelationshipVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RelationshipMapper extends BaseMapper<Relationship> {
    List<RelationshipInfo> searchRelationship(@Param("relationshipVo") RelationshipVo relationshipVo);

    void add(@Param("relationshipVo") RelationshipVo relationshipVo);

    void update(@Param("relationshipVo") RelationshipVo relationshipVo);
}
