package com.ding.hyld.info;

import com.ding.hyld.utils.TimeUtils;

import java.time.LocalDateTime;

public class MusicInfo {
    private Integer id;

    private String cover;
    private String name;
    private String info;
    private String lyrics;
    private String composing;
    private String sing;
    private String album;
    private LocalDateTime releaseTime;
    private String releaseTimeStr;
    private String audio;
    private String audioLink;
    private String mv;
    private String mvLink;
    private DictionaryInfo status;

    private LocalDateTime createTime;
    private String createTimeStr;
    private String note;

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public String getMvLink() {
        return mvLink;
    }

    public void setMvLink(String mvLink) {
        this.mvLink = mvLink;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getComposing() {
        return composing;
    }

    public void setComposing(String composing) {
        this.composing = composing;
    }

    public String getSing() {
        return sing;
    }

    public void setSing(String sing) {
        this.sing = sing;
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
        setReleaseTimeStr(TimeUtils.toString(releaseTime,TimeUtils.FORMAT_1));
    }

    public String getReleaseTimeStr() {
        return releaseTimeStr;
    }

    public void setReleaseTimeStr(String releaseTimeStr) {
        this.releaseTimeStr = releaseTimeStr;
    }

    public String getMv() {
        return mv;
    }

    public void setMv(String mv) {
        this.mv = mv;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        setCreateTimeStr(TimeUtils.toString(createTime,TimeUtils.FORMAT_1));
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }


    public DictionaryInfo getStatus() {
        return status;
    }

    public void setStatus(DictionaryInfo status) {
        this.status = status;
    }
}
