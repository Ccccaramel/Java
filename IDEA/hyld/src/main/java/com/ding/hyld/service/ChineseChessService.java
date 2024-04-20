package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.ChatFile;
import com.ding.hyld.entity.ChineseChess;
import com.ding.hyld.info.ChatFileInfo;
import com.ding.hyld.info.ChineseChessInfo;
import com.ding.hyld.vo.ChatFileVo;
import com.ding.hyld.vo.ChineseChessVo;

import java.util.List;

public interface ChineseChessService extends IService<ChineseChess> {
    void add(ChineseChessVo chineseChessVo);

    List<ChineseChessInfo> searchChineseChess(ChineseChessVo chineseChessVo);

    /**
     * 获取指定用户的信息以及历史战绩数据
     * @param userId
     * @return
     */
    ChineseChessInfo getBattleInfo(Integer userId);
}
