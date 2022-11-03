package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StarPowerMapper extends BaseMapper<StarPower> {
    List<StarPowerInfo> searchStarPower(@Param("page") Page page, @Param("starPowerVo") StarPowerVo starPowerVo);

    void update(@Param("starPowerVo") StarPowerVo starPowerVo);

    void add(@Param("starPowerVo") StarPowerVo starPowerVo);

    List<StarPowerInfo> findPithyInfoByGameRoleId(Integer id);
}
