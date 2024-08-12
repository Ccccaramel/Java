package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.ChineseChess;
import com.ding.office.info.ChineseChessInfo;
import com.ding.office.vo.ChineseChessVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChineseChessMapper extends BaseMapper<ChineseChess> {
    void add(@Param("chineseChessVo") ChineseChessVo chineseChessVo);

    List<ChineseChessInfo> searchChineseChess(@Param("chineseChessVo") ChineseChessVo chineseChessVo);

    List<ChineseChessInfo> getBattleInfo(Integer userId);

    void settlement(@Param("chineseChessVo") ChineseChessVo chineseChessVo);
}
