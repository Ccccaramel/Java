package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.ChineseChess;
import com.ding.hyld.entity.ChineseChessSnapshot;
import com.ding.hyld.info.ChineseChessInfo;
import com.ding.hyld.info.ChineseChessSnapshotInfo;
import com.ding.hyld.mapper.ChineseChessMapper;
import com.ding.hyld.mapper.ChineseChessSnapshotMapper;
import com.ding.hyld.service.ChineseChessService;
import com.ding.hyld.service.ChineseChessSnapshotService;
import com.ding.hyld.vo.ChineseChessSnapshotVo;
import com.ding.hyld.vo.ChineseChessVo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChineseChessServiceImpl extends ServiceImpl<ChineseChessMapper, ChineseChess> implements ChineseChessService {
    @Override
    public void add(ChineseChessVo chineseChessVo) {
        baseMapper.add(chineseChessVo);
    }

    @Override
    public List<ChineseChessInfo> searchChineseChess(ChineseChessVo chineseChessVo) {
        return baseMapper.searchChineseChess(chineseChessVo);
    }

    @Override
    public ChineseChessInfo getBattleInfo(Integer userId) {
        ChineseChessInfo info =new ChineseChessInfo();
        Integer i=0;
        List<ChineseChessInfo> list = baseMapper.getBattleInfo(userId);
        for (ChineseChessInfo chineseChessInfo :list) {
            if(chineseChessInfo.getWinner().equals(userId)){
                i++;
            }
        }
        info.setChanceOfWinning(list.size()==0?0:i*100 / list.size());
        info.setTotalGames(list.size());
        return info;
    }

}
