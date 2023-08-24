package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Music;
import com.ding.hyld.info.MusicInfo;
import com.ding.hyld.mapper.MusicMapper;
import com.ding.hyld.service.MusicService;
import com.ding.hyld.vo.MusicVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {
    @Override
    public void add(MusicVo musicVo) {
        baseMapper.add(musicVo);
    }

    @Override
    public List<MusicInfo> searchMusic(MusicVo musicVo) {
        return baseMapper.searchMusic(musicVo);
    }

}
