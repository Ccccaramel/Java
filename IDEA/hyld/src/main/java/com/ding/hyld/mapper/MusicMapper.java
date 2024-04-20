package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Music;
import com.ding.hyld.info.MusicInfo;
import com.ding.hyld.vo.MusicVo;
import com.ding.hyld.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MusicMapper extends BaseMapper<Music> {
    void add(@Param("musicVo") MusicVo musicVo);

    List<MusicInfo> searchMusic(@Param("musicVo") MusicVo musicVo,@Param("page") Page page);

    Integer searchMusicOfPage(@Param("musicVo") MusicVo musicVo);

    void updateStatus(@Param("musicVo") MusicVo musicVo);

    void update(@Param("musicVo") MusicVo musicVo);

    MusicInfo findBy(@Param("musicVo") MusicVo musicVo);

    List<MusicInfo> getAll(@Param("musicVo") MusicVo musicVo);
}
