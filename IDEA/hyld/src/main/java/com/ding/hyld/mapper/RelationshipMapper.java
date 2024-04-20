package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Relationship;
import com.ding.hyld.info.RelationshipInfo;
import com.ding.hyld.vo.RelationshipVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RelationshipMapper extends BaseMapper<Relationship> {
    List<RelationshipInfo> searchRelationship(@Param("relationshipVo") RelationshipVo relationshipVo);

    void add(@Param("relationshipVo") RelationshipVo relationshipVo);

    void update(@Param("relationshipVo") RelationshipVo relationshipVo);
}
