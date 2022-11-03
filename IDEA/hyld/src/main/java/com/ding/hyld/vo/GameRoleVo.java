package com.ding.hyld.vo;

import com.ding.hyld.info.GadgetInfo;
import com.ding.hyld.info.NormalAttackInfo;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.info.SuperSkillInfo;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class GameRoleVo {
    private Integer id;
    private String name;
    private String title;
    private String headImg; // 头像
    private String portrait; // 肖像
    private String headImgUrl; // 头像 link
    private String portraitUrl; // 肖像 link
    private Integer rarity; // 稀有度
    private Integer position; // 定位
    private String profile; // 角色简介
    private String launchTime; // 上线时间
    private String launchTimeStr; // 上线时间
    private String hp; // 生命值
    private String speed; // 移速
    private String inborn; // 天赋
    private Integer basicForm; // 基础形态
    private Integer sequence; // 顺序(非基础形态则需要对其排序)
    private String createTime;
    private String createTimeStr;
    private String note;
    private boolean add;
    private boolean basic; // 是否为基础形态



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(String launchTime) {
        this.launchTime = launchTime;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getInborn() {
        return inborn;
    }

    public void setInborn(String inborn) {
        this.inborn = inborn;
    }

    public Integer getBasicForm() {
        return basicForm;
    }

    public void setBasicForm(Integer basicForm) {
        this.basicForm = basicForm;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLaunchTimeStr() {
        return launchTimeStr;
    }

    public void setLaunchTimeStr(String launchTimeStr) {
        this.launchTimeStr = launchTimeStr;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public boolean isBasic() {
        return basic;
    }

    public void setBasic(boolean basic) {
        this.basic = basic;
    }
}
