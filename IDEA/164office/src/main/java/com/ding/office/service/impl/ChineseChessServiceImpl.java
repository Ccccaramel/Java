package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.ChineseChess;
import com.ding.office.info.ChineseChessInfo;
import com.ding.office.mapper.ChineseChessMapper;
import com.ding.office.service.ChineseChessService;
import com.ding.office.vo.ChineseChessVo;
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
            if(chineseChessInfo.getWinner()!=null&&chineseChessInfo.getWinner().getId().equals(userId)){
                i++;
            }
        }
        info.setChanceOfWinning(list.size()==0?0:i*100 / list.size());
        info.setTotalGames(list.size());
        return info;
    }

    @Override
    public void settlement(ChineseChessVo chineseChessVo) {
        baseMapper.settlement(chineseChessVo);
    }

}
