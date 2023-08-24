package com.ding.hyld.constant;

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

    String IBS_KEY = "VQPBZ-GZIKU-QNPV7-B7MD5-PPA2F-TMBES";

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

            "/vL/sVL",

            "/common/getPublicKey",

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

            "/blog/searchBlog",
            "/blog/findBlogById",
            "/blog/viewBlog",
            "/blogRemark/searchBlogRemark",
    };

    String FORM_EMAIL = "by164office@163.com"; // 平台发送验证码专用邮箱

    String STRING_1 = "急冻树，急冻树，好吃的~急冻树~";
    String STRING_2 = "急冻树!";
}
