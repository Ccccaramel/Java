package com.ding.hyld.info;

import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.utils.TimeUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class GameRoleInfo {
    private Integer id;
    private String name;
    private String headImg; // 头像
    private String portrait; // 肖像
    private String headImgUrl; // 头像 link
    private String portraitUrl; // 肖像 link
    private Dictionary rarity; // 稀有度
    private Dictionary position; // 定位
    private String profile; // 角色简介
    private String launchTime; // 上线时间
    private String launchTimeStr; // 上线时间
    private String hp; // 生命值
    private String speed; // 移速
    private String inborn; // 天赋
    private GameRoleInfo basicForm; // 基础形态
    private GameRoleInfo secondaryForm; // 次级形态
    private Integer sequence; // 顺序(非基础形态则需要对其排序)
    private Integer popular; // 受欢迎
    private Integer unpopular; // 不受欢迎
    private String createTime;
    private String createTimeStr;
    private String note;

    private NormalAttackInfo normalAttackInfo; // 普通攻击
    private List<SuperSkillInfo> superSkillInfo; // 超级技能
    private List<GadgetInfo> gadgetInfoList; // 随身妙具
    private List<StarPowerInfo> starPowerInfoList; // 星徽之力

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
        if(StringUtils.hasText(headImg)){
            setHeadImgUrl(ResourcesPathUtils.getPhotoPath() + headImg);
        }
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
        if(StringUtils.hasText(portrait)){
            setPortraitUrl(ResourcesPathUtils.getPhotoPath() +portrait);
        }
    }

    public Dictionary getRarity() {
        return rarity;
    }

    public void setRarity(Dictionary rarity) {
        this.rarity = rarity;
    }

    public Dictionary getPosition() {
        return position;
    }

    public void setPosition(Dictionary position) {
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

    public void setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime.toString();
        this.setLaunchTimeStr(TimeUtils.toString(launchTime,TimeUtils.FORMAT_1));
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

    public GameRoleInfo getBasicForm() {
        return basicForm;
    }

    public void setBasicForm(GameRoleInfo basicForm) {
        this.basicForm = basicForm;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }

    public Integer getUnpopular() {
        return unpopular;
    }

    public void setUnpopular(Integer unpopular) {
        this.unpopular = unpopular;
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

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime.toString();
        this.setCreateTimeStr(TimeUtils.toString(createTime,TimeUtils.FORMAT_1));
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

    public GameRoleInfo getSecondaryForm() {
        return secondaryForm;
    }

    public void setSecondaryForm(GameRoleInfo secondaryForm) {
        this.secondaryForm = secondaryForm;
    }

    public NormalAttackInfo getNormalAttackInfo() {
        return normalAttackInfo;
    }

    public void setNormalAttackInfo(NormalAttackInfo normalAttackInfo) {
        this.normalAttackInfo = normalAttackInfo;
    }

    public List<SuperSkillInfo> getSuperSkillInfo() {
        return superSkillInfo;
    }

    public void setSuperSkillInfo(List<SuperSkillInfo> superSkillInfo) {
        this.superSkillInfo = superSkillInfo;
    }

    public List<GadgetInfo> getGadgetInfoList() {
        return gadgetInfoList;
    }

    public void setGadgetInfoList(List<GadgetInfo> gadgetInfoList) {
        this.gadgetInfoList = gadgetInfoList;
    }

    public List<StarPowerInfo> getStarPowerInfoList() {
        return starPowerInfoList;
    }

    public void setStarPowerInfoList(List<StarPowerInfo> starPowerInfoList) {
        this.starPowerInfoList = starPowerInfoList;
    }

    @Override
    public String toString() {
        return "GameRoleInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headImg='" + headImg + '\'' +
                ", portrait='" + portrait + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", portraitUrl='" + portraitUrl + '\'' +
                ", rarity=" + rarity +
                ", position=" + position +
                ", profile='" + profile + '\'' +
                ", launchTime='" + launchTime + '\'' +
                ", launchTimeStr='" + launchTimeStr + '\'' +
                ", hp='" + hp + '\'' +
                ", speed='" + speed + '\'' +
                ", inborn='" + inborn + '\'' +
                ", basicForm=" + basicForm +
                ", secondaryForm=" + secondaryForm +
                ", sequence=" + sequence +
                ", popular=" + popular +
                ", unpopular=" + unpopular +
                ", createTime='" + createTime + '\'' +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", note='" + note + '\'' +
                ", normalAttackInfo=" + normalAttackInfo +
                ", superSkillInfo=" + superSkillInfo +
                ", gadgetInfoList=" + gadgetInfoList +
                ", starPowerInfoList=" + starPowerInfoList +
                '}';
    }
}
