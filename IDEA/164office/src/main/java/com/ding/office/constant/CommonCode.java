package com.ding.office.constant;

import java.util.HashMap;
import java.util.Map;

public interface CommonCode {
    /**
     * jwt 过期时间1天
     */
    long EXPIRE_TIME= 24*60*60*1000L;

    /**
     * jwt 加密密钥
     */
    String JWT_TOKEN = "s211506164";

    String SLAT = "ding";

    String QQ_IP_IBS_KEY = "VQPBZ-GZIKU-QNPV7-B7MD5-PPA2F-TMBES";

    String BAIDU_WEATHER_IBS_URL = "https://api.map.baidu.com/weather/v1/?";
    String BAIDU_WEATHER_IBS_AK = "ZR0nto7NC6n1huyU32Q1zPzlHffRm5br";
    /**
     * 放行的 URL
     */
    String[] OPEN_URL = {
            "/static/**", // 放行静态资源
//            "/photo/**", // 放行静态资源
            "/resources/**", // 放行静态资源
//            "/static/ppp.jpg",
//            "/upload/**", // 放行静态资源
            "/api/**", // 放行静态资源
//            "/hyld/**", // 放行静态资源

            "/dictionary/getTeamCompetitionType",
            "/dictionary/getTeamType",
            "/dictionary/findLeaveType",
            "/dictionary/findJoinWayType",
            "/dictionary/findLeaveType",
            "/dictionary/getTeamStatusType",
            "/dictionary/findJoinWayType",
            "/dictionary/findLeaveType",
            "/dictionary/getTeamStatusType",
            "/dictionary/getBlogType",

            "/team/searchTeamInfo",
            "/userWithTeam/searchValidTeamInfo",

            "/player/searchPlayerInfo",

            "/updateLog/searchUpdateLog",

            "/topic/searchTopic",
            "/topic/getTopicData",

            "/pointer/searchPointer",

            "/gameRole/searchGameRoleByClass",
            "/gameRole/searchGameRoleInfoById",

            "/gameRoleComment/getGameRoleCommentData",

            "/gameRolePopularity/searchGameRolePopularity",

            "/welcome/myHumbleAbode",

            "/common/getPublicKey",
            "/common/getWeather",

            "/systemConfig/getHomeNotice",
            "/systemConfig/getCommunityNotice",

            "/credit/getTeamData",
            "/credit/searchCreditBy",

            "/gear/searchGear",

            "/officialVersionUpdateLog/searchOfficialVersionUpdateLog",

            "/pointer/searchPointer",

//            "/user/getCurrentUserInfo",

            "/emailCode/sendEmailCode",
            "/emailCode/emailVerify",

            "/user/register",
            "/user/login",
            "/user/qqLogin",
            "/user/updatePassword",
            "/user/generate",

            "/blog/searchBlog",
            "/blog/findBlogById",
            "/blog/viewBlog",
            "/blogRemark/searchBlogRemark",

            "/music/searchMusic",
            "/music/getMusic",

            "/chat/searchChat",

            "/linkChat/*",  // 必须放开!
            "/linkChineseChess/*",
    };

    String FORM_EMAIL = "by164office@163.com"; // 平台发送验证码专用邮箱

    String STRING_1 = "急冻树，急冻树，好吃的~急冻树~";
    String STRING_2 = "急冻树!";

    Map<Integer,String> accessType=new HashMap<Integer,String>(){{
        put(0, "访问首页");
        put(1, "访问【战队竞赛奖励一览】");
        put(2, "访问【社区】");
        put(3, "编辑【博客】");
        put(4, "访问【参考前必读!】");
        put(5, "访问【找到你】");
        put(6, "访问【游戏角色】");
        put(7, "访问【角色信息】");
        put(8, "访问【强化装备】");
        put(9, "访问【贪吃蛇】");
        put(10, "访问【乱斗金券奖励一览】");
        put(11, "访问【荣誉联赛星光奖励一览】");
        put(12, "访问【荣誉之路奖励一览】");
        put(13, "访问【官方版本更新日志】");
        put(14, "访问【反馈与建议】");
        put(15, "QQ注册/登录中");
        put(16, "访问【玩家】");
        put(17, "访问【战队】");
        put(18, "浏览【博客】");
        put(19, "访问【星光联赛奖励一览】");
        put(20, "访问【俄罗斯方块】");
        put(21, "浏览【话题】");
        put(22, "访问【水果机】");
        put(23, "访问【扫雷】");
        put(24, "访问【Music列表】");
        put(25, "进入【Music】");
        put(26, "播放【Music】");
    }};

    String SEC_WEBSOCKET_PROTOCOL="Sec-Websocket-Protocol";
}
