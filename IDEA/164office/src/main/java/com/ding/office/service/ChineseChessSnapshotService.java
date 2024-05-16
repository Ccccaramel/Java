package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.ChineseChessSnapshot;
import com.ding.office.info.ChineseChessSnapshotInfo;
import com.ding.office.vo.ChineseChessSnapshotVo;

import java.util.List;

public interface ChineseChessSnapshotService extends IService<ChineseChessSnapshot> {
    void add(ChineseChessSnapshotVo chineseChessSnapshotVo);

    List<ChineseChessSnapshotInfo> searchChineseChessSnapshot(ChineseChessSnapshotVo chineseChessSnapshotVo);
}
