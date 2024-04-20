package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.ChineseChessSnapshot;
import com.ding.hyld.info.ChineseChessSnapshotInfo;
import com.ding.hyld.vo.ChineseChessSnapshotVo;

import java.util.List;

public interface ChineseChessSnapshotService extends IService<ChineseChessSnapshot> {
    void add(ChineseChessSnapshotVo chineseChessSnapshotVo);

    List<ChineseChessSnapshotInfo> searchChineseChessSnapshot(ChineseChessSnapshotVo chineseChessSnapshotVo);
}
