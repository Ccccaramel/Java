package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.MusicLyric;
import com.ding.office.info.MusicLyricInfo;
import com.ding.office.vo.MusicLyricVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface MusicLyricService extends IService<MusicLyric> {
    void add(MusicLyricVo musicLyricVo);

    List<MusicLyricInfo> searchMusicLyric(MusicLyricVo musicLyricVo, Page page);

    void update(MusicLyricVo musicLyricVo);

    Integer searchMusicLyricOfPage(MusicLyricVo musicLyricVo);
}
