package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.NormalAttack;
import com.ding.office.info.NormalAttackInfo;
import com.ding.office.vo.NormalAttackVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NormalAttackMapper extends BaseMapper<NormalAttack> {
    List<NormalAttackInfo> searchNormalAttack(@Param("page") Page page, @Param("normalAttackVo") NormalAttackVo normalAttackVo);

    void update(@Param("normalAttackVo") NormalAttackVo normalAttackVo);

    void add(@Param("normalAttackVo") NormalAttackVo normalAttackVo);

    NormalAttackInfo findById(Integer id);

    void delete(@Param("normalAttackVo") NormalAttackVo normalAttackVo);

    NormalAttackInfo findPithyInfoByGameRoleId(Integer id);

    Integer searchNormalAttackOfPage(@Param("normalAttackVo") NormalAttackVo normalAttackVo);
}
