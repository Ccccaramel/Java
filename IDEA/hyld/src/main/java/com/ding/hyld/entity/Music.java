package com.ding.hyld.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ding.hyld.entity.base.BaseObject;

import java.time.LocalDateTime;

@TableName("music")
public class Music extends BaseObject {
    private String coverRef;
    private String name;
    private String composing;
    private String lyrics;
    private String arranger;
    private String singer;
    private String info;
    private String album;
    private LocalDateTime releaseTime;
    private String audioName;
    private String audioRef;
    private String mvLink;
    private Integer status;

    public String getCoverRef() {
        return coverRef;
    }

    public void setCoverRef(String coverRef) {
        this.coverRef = coverRef;
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

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime = releaseTime;
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
    }

    public String getMvLink() {
        return mvLink;
    }

    public void setMvLink(String mvLink) {
        this.mvLink = mvLink;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
