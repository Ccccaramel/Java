package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Music;
import com.ding.hyld.info.MusicInfo;
import com.ding.hyld.info.MusicLyricInfo;
import com.ding.hyld.mapper.MusicMapper;
import com.ding.hyld.service.MusicService;
import com.ding.hyld.vo.MusicVo;
import com.ding.hyld.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {
    @Override
    public void add(MusicVo musicVo) {
        baseMapper.add(musicVo);
    }

    @Override
    public List<MusicInfo> searchMusic(MusicVo musicVo, Page page) {
        return baseMapper.searchMusic(musicVo,page);
    }

    @Override
    public Integer searchMusicOfPage(MusicVo musicVo) {
        return baseMapper.searchMusicOfPage(musicVo);
    }

    @Override
    public void updateStatus(MusicVo musicVo) {
        baseMapper.updateStatus(musicVo);
    }

    @Override
    public void update(MusicVo musicVo) {
        baseMapper.update(musicVo);
    }

    @Override
    public MusicInfo findBy(MusicVo musicVo) {
        return baseMapper.findBy(musicVo);
    }

    @Override
    public List<MusicInfo> getAll(MusicVo musicVo) {
        return baseMapper.getAll(musicVo);
    }

}
