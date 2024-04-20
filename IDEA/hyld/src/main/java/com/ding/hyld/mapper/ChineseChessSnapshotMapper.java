package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.ChineseChessSnapshot;
import com.ding.hyld.info.ChineseChessSnapshotInfo;
import com.ding.hyld.vo.ChineseChessSnapshotVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChineseChessSnapshotMapper extends BaseMapper<ChineseChessSnapshot> {
    void add(@Param("chineseChessSnapshotVo") ChineseChessSnapshotVo chineseChessSnapshotVo);

    List<ChineseChessSnapshotInfo> searchChineseChessSnapshot(@Param("chineseChessSnapshotVo") ChineseChessSnapshotVo chineseChessSnapshotVo);
}
