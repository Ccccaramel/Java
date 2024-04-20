package com.ding.hyld.controller;

import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.MusicLyricService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.MusicLyricVo;
import com.ding.hyld.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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