package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.MusicLyric;
import com.ding.hyld.info.MusicLyricInfo;
import com.ding.hyld.mapper.MusicLyricMapper;
import com.ding.hyld.service.MusicLyricService;
import com.ding.hyld.vo.MusicLyricVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceLyricImpl extends ServiceImpl<MusicLyricMapper, MusicLyric> implements MusicLyricService {
    @Override
    public void add(MusicLyricVo musicLyricVo) {
        baseMapper.add(musicLyricVo);
    }

    @Override
    public List<MusicLyricInfo> searchMusicLyric(MusicLyricVo musicLyricVo) {
        return baseMapper.searchMusicLyric(musicLyricVo);
    }

}
