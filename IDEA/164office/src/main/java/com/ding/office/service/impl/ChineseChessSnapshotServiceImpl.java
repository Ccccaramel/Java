package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.ChineseChessSnapshot;
import com.ding.office.info.ChineseChessSnapshotInfo;
import com.ding.office.mapper.ChineseChessSnapshotMapper;
import com.ding.office.service.ChineseChessSnapshotService;
import com.ding.office.vo.ChineseChessSnapshotVo;
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
