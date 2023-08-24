package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.MusicLyric;
import com.ding.hyld.info.MusicLyricInfo;
import com.ding.hyld.vo.MusicLyricVo;

import java.util.List;

public interface MusicLyricService extends IService<MusicLyric> {
    void add(MusicLyricVo musicLyricVo);

    List<MusicLyricInfo> searchMusicLyric(MusicLyricVo musicLyricVo);
}
