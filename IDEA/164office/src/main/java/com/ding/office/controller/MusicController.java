package com.ding.office.controller;

import com.alibaba.fastjson.JSONObject;
import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.info.MusicInfo;
import com.ding.office.service.MusicService;
import com.ding.office.utils.R;
import com.ding.office.utils.ResourceUploadAndDownloadUtils;
import com.ding.office.utils.ResourcesPathUtils;
import com.ding.office.vo.MusicVo;
import com.ding.office.vo.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/music")
public class MusicController extends BaseController {
    @Autowired
    private MusicService musicService;

    @PostMapping("/saveMusic")
    public R saveMusic(@RequestParam("musicVoStr") String musicVoStr, MultipartFile cover, MultipartFile audio){
        HashMap<String,Object> result=new HashMap<>();
        Map<String, String> res;
        String msg;

        if(!isLogin()){
            return R.fail("请先登录!");
        }

        if(!isRoot()){
            return R.fail("暂无权限!");
        }

        // 解析
        ObjectMapper objectMapper = new ObjectMapper();
        MusicVo musicVo =objectMapper.convertValue(JSONObject.parse(musicVoStr),MusicVo.class);

        // 2.将解析整理资源并存储,并返回资源信息
        ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
                ResourcesPathUtils.MUSIC,
                ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.MUSIC),
                ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.MUSIC),
                ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.MUSIC),
                ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.MUSIC));

        if(musicVo.isAdd()){
            res = resourceUploadAndDownload.resourceUpload(cover, getUserId());
            musicVo.setCover(res.get("fileName"));
            musicVo.setCoverRef(res.get("newName"));

            res = resourceUploadAndDownload.resourceUpload(audio, getUserId());
            musicVo.setAudioName(res.get("fileName"));
            musicVo.setAudioRef(res.get("newName"));

            musicVo.setStatus(DictionaryCode.MUSIC_STATUS_1);
            musicService.add(musicVo);
            msg="新增成功!";
        }
        else{
            if(cover!=null){
                res = resourceUploadAndDownload.resourceUpload(cover, getUserId());
                musicVo.setCoverRef(res.get("newName"));
            }
            if(audio!=null){
                res = resourceUploadAndDownload.resourceUpload(audio, getUserId());
                musicVo.setAudioName(res.get("fileName"));
                musicVo.setAudioRef(res.get("newName"));
            }

            musicService.update(musicVo);
            msg="编辑成功!";
        }

        return R.success(msg);
    }

    @GetMapping("/searchMusic")
    public R searchMusic(MusicVo musicVo, Page page){
        HashMap<String,Object> result=new HashMap<>();
        musicVo.setStatus(DictionaryCode.MUSIC_STATUS_1);

        result.put("data",musicService.searchMusic(musicVo,page));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(musicService.searchMusicOfPage(musicVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/restoreMusic")
    public R restoreMusic(@RequestBody MusicVo musicVo){
        musicVo.setStatus(DictionaryCode.MUSIC_STATUS_1);
        musicService.updateStatus(musicVo);
        return R.success("music已恢复!");
    }


    @PostMapping("/deleteMusic")
    public R deleteMusic(@RequestBody MusicVo musicVo){
        musicVo.setStatus(DictionaryCode.MUSIC_STATUS_2);
        musicService.updateStatus(musicVo);
        return R.success("music已删除!");
    }

    /**
     * 获取指定id的music,当id为0时,将随机获取
     * @param id
     * @return
     */
    @GetMapping("/getMusic")
    public R getMusic(Integer id){
        HashMap<String,Object> result=new HashMap<>();
        MusicVo musicVo=new MusicVo();
        musicVo.setStatus(DictionaryCode.MUSIC_STATUS_1);

        if(id==0){  // 随机获取
            List<MusicInfo> list=musicService.getAll(musicVo);
            int total=list.size();
            int i = (int) (Math.floor(Math.random() * total));
            musicVo.setId(list.get(i).getId());
        }
        else{
            musicVo.setId(id);
        }

        MusicInfo musicInfo=musicService.findBy(musicVo);
        result.put("data",musicInfo);
        return R.success(result);
    }
}