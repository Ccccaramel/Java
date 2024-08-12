package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.Music;
import com.ding.office.info.MusicInfo;
import com.ding.office.mapper.MusicMapper;
import com.ding.office.service.MusicService;
import com.ding.office.vo.MusicVo;
import com.ding.office.vo.Page;
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

    @Override
    public void addHot(Integer id) {
        baseMapper.addHot(id);
    }

}
