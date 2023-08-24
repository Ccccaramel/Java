package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Music;
import com.ding.hyld.info.MusicInfo;
import com.ding.hyld.vo.MusicVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MusicMapper extends BaseMapper<Music> {
    void add(@Param("musicVo") MusicVo musicVo);

    List<MusicInfo> searchMusic(@Param("musicVo") MusicVo musicVo);
}
