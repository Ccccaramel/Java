package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.StarPower;
import com.ding.office.info.StarPowerInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.StarPowerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StarPowerMapper extends BaseMapper<StarPower> {
    List<StarPowerInfo> searchStarPower(@Param("page") Page page, @Param("starPowerVo") StarPowerVo starPowerVo);

    void update(@Param("starPowerVo") StarPowerVo starPowerVo);

    void add(@Param("starPowerVo") StarPowerVo starPowerVo);

    List<StarPowerInfo> findPithyInfoByGameRoleId(Integer id);

    Integer searchStarPowerOfPage(@Param("starPowerVo") StarPowerVo starPowerVo);
}
