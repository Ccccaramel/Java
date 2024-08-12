package com.ding.office.info;

import com.ding.office.entity.Dictionary;
import com.ding.office.utils.ResourcesPathUtils;
import com.ding.office.utils.TimeUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class MusicInfo {
    private Integer id;
    private String coverRef;
    private String coverLink;
    private String name;
    private String composing;
    private String lyrics;
    private String arranger;
    private String singer;
    private String info;
    private String album;
    private String releaseTime;
    private String releaseTimeStr;
    private String audioName;
    private String audioRef;
    private String audioLink;
    private String mvLink;
    private String note;
    private Dictionary status;

    private LocalDateTime createTime;
    private String createTimeStr;

    private List<MusicLyricInfo> musicLyricInfoList;

    private Integer hot;

    private Dictionary type;

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
    }

    public List<MusicLyricInfo> getMusicLyricInfoList() {
        return musicLyricInfoList;
    }

    public void setMusicLyricInfoList(List<MusicLyricInfo> musicLyricInfoList) {
        this.musicLyricInfoList = musicLyricInfoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoverRef() {
        return coverRef;
    }

    public void setCoverRef(String coverRef) {
        this.coverRef = coverRef;
        if(StringUtils.hasText(coverRef)){
            setCoverLink(ResourcesPathUtils.getPhotoPath(ResourcesPathUtils.MUSIC) + coverRef);
        }
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public String getCoverLink() {
        return coverLink;
    }

    public void setCoverLink(String coverLink) {
        this.coverLink = coverLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposing() {
        return composing;
    }

    public void setComposing(String composing) {
        this.composing = composing;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getArranger() {
        return arranger;
    }

    public void setArranger(String arranger) {
        this.arranger = arranger;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime =releaseTime.toString();
        setReleaseTimeStr(TimeUtils.toString(releaseTime,TimeUtils.FORMAT_4));
    }

    public String getReleaseTimeStr() {
        return releaseTimeStr;
    }

    public void setReleaseTimeStr(String releaseTimeStr) {
        this.releaseTimeStr = releaseTimeStr;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getAudioRef() {
        return audioRef;
    }

    public void setAudioRef(String audioRef) {
        this.audioRef = audioRef;
        if(StringUtils.hasText(audioRef)){
            setAudioLink(ResourcesPathUtils.getAudioPath(ResourcesPathUtils.MUSIC) + audioRef);
        }
    }

    public String getMvLink() {
        return mvLink;
    }

    public void setMvLink(String mvLink) {
        this.mvLink = mvLink;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Dictionary getStatus() {
        return status;
    }

    public void setStatus(Dictionary status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        setCreateTimeStr(TimeUtils.toString(createTime,TimeUtils.FORMAT_1));
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }
}
