package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.ChineseChessSnapshot;
import com.ding.hyld.info.ChineseChessSnapshotInfo;
import com.ding.hyld.mapper.ChineseChessSnapshotMapper;
import com.ding.hyld.service.ChineseChessSnapshotService;
import com.ding.hyld.vo.ChineseChessSnapshotVo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChineseChessSnapshotServiceImpl extends ServiceImpl<ChineseChessSnapshotMapper, ChineseChessSnapshot> implements ChineseChessSnapshotService {
    @Override
    public void add(ChineseChessSnapshotVo chineseChessSnapshotVo) {
        baseMapper.add(chineseChessSnapshotVo);
    }

    @Override
    public List<ChineseChessSnapshotInfo> searchChineseChessSnapshot(ChineseChessSnapshotVo chineseChessSnapshotVo) {
        return baseMapper.searchChineseChessSnapshot(chineseChessSnapshotVo);
    }
}
