package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.MusicLyric;
import com.ding.hyld.info.MusicLyricInfo;
import com.ding.hyld.vo.MusicLyricVo;
import com.ding.hyld.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MusicLyricMapper extends BaseMapper<MusicLyric> {
    void add(@Param("musicLyricVo") MusicLyricVo musicLyricVo);

    List<MusicLyricInfo> searchMusicLyric(@Param("musicLyricVo") MusicLyricVo musicLyricVo,@Param("page")  Page page);

    void update(@Param("musicLyricVo") MusicLyricVo musicLyricVo);

    Integer searchMusicLyricOfPage(@Param("musicLyricVo") MusicLyricVo musicLyricVo);
}
