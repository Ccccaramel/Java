package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.MusicLyric;
import com.ding.office.info.MusicLyricInfo;
import com.ding.office.mapper.MusicLyricMapper;
import com.ding.office.service.MusicLyricService;
import com.ding.office.vo.MusicLyricVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicLyricServiceImpl extends ServiceImpl<MusicLyricMapper, MusicLyric> implements MusicLyricService {
    @Override
    public void add(MusicLyricVo musicLyricVo) {
        baseMapper.add(musicLyricVo);
    }

    @Override
    public List<MusicLyricInfo> searchMusicLyric(MusicLyricVo musicLyricVo, Page page) {
        return baseMapper.searchMusicLyric(musicLyricVo,page);
    }

    @Override
    public void update(MusicLyricVo musicLyricVo) {
        baseMapper.update(musicLyricVo);
    }

    @Override
    public Integer searchMusicLyricOfPage(MusicLyricVo musicLyricVo) {
        return baseMapper.searchMusicLyricOfPage(musicLyricVo);
    }

}
