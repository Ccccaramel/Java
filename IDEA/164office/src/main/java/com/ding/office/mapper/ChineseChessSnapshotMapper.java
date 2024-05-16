package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.ChineseChessSnapshot;
import com.ding.office.info.ChineseChessSnapshotInfo;
import com.ding.office.vo.ChineseChessSnapshotVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChineseChessSnapshotMapper extends BaseMapper<ChineseChessSnapshot> {
    void add(@Param("chineseChessSnapshotVo") ChineseChessSnapshotVo chineseChessSnapshotVo);

    List<ChineseChessSnapshotInfo> searchChineseChessSnapshot(@Param("chineseChessSnapshotVo") ChineseChessSnapshotVo chineseChessSnapshotVo);
}
