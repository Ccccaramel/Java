package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.NormalAttack;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.info.NormalAttackInfo;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.vo.NormalAttackVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
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
