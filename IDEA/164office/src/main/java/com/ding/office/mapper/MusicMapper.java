package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Music;
import com.ding.office.info.MusicInfo;
import com.ding.office.vo.MusicVo;
import com.ding.office.vo.Page;
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
