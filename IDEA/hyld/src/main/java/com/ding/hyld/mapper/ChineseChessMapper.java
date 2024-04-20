package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.ChatFile;
import com.ding.hyld.entity.ChineseChess;
import com.ding.hyld.info.ChatFileInfo;
import com.ding.hyld.info.ChineseChessInfo;
import com.ding.hyld.vo.ChatFileVo;
import com.ding.hyld.vo.ChineseChessVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChineseChessMapper extends BaseMapper<ChineseChess> {
    void add(@Param("chineseChessVo") ChineseChessVo chineseChessVo);

    List<ChineseChessInfo> searchChineseChess(@Param("chineseChessVo") ChineseChessVo chineseChessVo);

    List<ChineseChessInfo> getBattleInfo(Integer userId);
}
