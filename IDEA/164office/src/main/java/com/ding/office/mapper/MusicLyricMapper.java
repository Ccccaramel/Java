package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.MusicLyric;
import com.ding.office.info.MusicLyricInfo;
import com.ding.office.vo.MusicLyricVo;
import com.ding.office.vo.Page;
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
