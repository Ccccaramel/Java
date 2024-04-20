package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Music;
import com.ding.hyld.info.MusicInfo;
import com.ding.hyld.vo.MusicVo;
import com.ding.hyld.vo.Page;

import java.util.List;

public interface MusicService extends IService<Music> {
    void add(MusicVo musicVo);

    List<MusicInfo> searchMusic(MusicVo musicVo, Page page);

    Integer searchMusicOfPage(MusicVo musicVo);

    void updateStatus(MusicVo musicVo);

    void update(MusicVo musicVo);

    MusicInfo findBy(MusicVo musicVo);

    List<MusicInfo> getAll(MusicVo musicVo);
}
