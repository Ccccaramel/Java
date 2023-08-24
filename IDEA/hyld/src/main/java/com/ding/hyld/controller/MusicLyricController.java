package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.MusicLyricService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.MusicLyricVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/musicLyric")
public class MusicLyricController extends BaseController {
    @Autowired
    private MusicLyricService musicLyricService;

    @PostMapping("/saveMusicLyric")
    public R saveMusicLyric(MusicLyricVo musicLyricVo){
        if(!isLogin()){
            return R.fail("请先登录!");
        }

        if(!isRoot()){
            return R.fail("暂无权限!");
        }

        musicLyricVo.setStatus(DictionaryCode.BLOG_FILE_STATUS_1);

        musicLyricService.add(musicLyricVo);

        return R.fail("歌词添加成功!");
    }
}