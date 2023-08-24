package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.MusicLyric;
import com.ding.hyld.info.MusicLyricInfo;
import com.ding.hyld.vo.MusicLyricVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MusicLyricMapper extends BaseMapper<MusicLyric> {
    void add(@Param("musicLyricVo") MusicLyricVo musicLyricVo);

    List<MusicLyricInfo> searchMusicLyric(@Param("musicLyricVo") MusicLyricVo musicLyricVo);
}
