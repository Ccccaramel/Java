package com.ding.office.controller;

import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.MusicLyricService;
import com.ding.office.utils.R;
import com.ding.office.vo.MusicLyricVo;
import com.ding.office.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/musicLyric")
public class MusicLyricController extends BaseController {
    @Autowired
    private MusicLyricService musicLyricService;

    @PostMapping("/saveMusicLyric")
    public R saveMusicLyric(@RequestBody MusicLyricVo musicLyricVo){
        if(!isLogin()){
            return R.fail("请先登录!");
        }

        if(!isRoot()){
            return R.fail("暂无权限!");
        }

        if(musicLyricVo.isAdd()){
            musicLyricVo.setStatus(DictionaryCode.MUSIC_LYRIC_STATUS_1);
            musicLyricService.add(musicLyricVo);
            return R.success("歌词添加成功!");
        }
        else{
            musicLyricService.update(musicLyricVo);
            return R.success("歌词编辑成功!");
        }
    }

    @GetMapping("/getMusicLyric")
    public R getMusicLyric(MusicLyricVo musicLyricVo, Page page){
        HashMap<String,Object> result=new HashMap<>();

        result.put("data",musicLyricService.searchMusicLyric(musicLyricVo,page));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(musicLyricService.searchMusicLyricOfPage(musicLyricVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/deleteMusicLyric")
    public R updateMusicStatus(MusicLyricVo musicLyricVo){
        musicLyricVo.setStatus(DictionaryCode.MUSIC_LYRIC_STATUS_2);
        musicLyricService.update(musicLyricVo);
        return R.success("歌词已删除!");
    }

    @PostMapping("/recoverMusicLyric")
    public R recoverMusicLyric(MusicLyricVo musicLyricVo){
        musicLyricVo.setStatus(DictionaryCode.MUSIC_LYRIC_STATUS_2);
        musicLyricService.update(musicLyricVo);
        return R.success("歌词已恢复!");
    }
}