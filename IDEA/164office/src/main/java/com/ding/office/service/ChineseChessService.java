package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.ChineseChess;
import com.ding.office.info.ChineseChessInfo;
import com.ding.office.vo.ChineseChessVo;

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
