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

    /**
     * 放行的 URL
     */
    String[] OPEN_URL = {
            "/static/**", // 放行静态资源
            "/photo/**", // 放行静态资源
            "/hyld/**", // 放行静态资源
            "/static/ppp.jpg",
            "/ppp.jpg",
            "/ppp",

            "/dictionary/getTeamCompetitionType",
            "/dictionary/getTeamType",
            "/dictionary/findLeaveType",
            "/dictionary/findJoinWayType",
            "/dictionary/findLeaveType",
            "/dictionary/getTeamStatusType",
            "/dictionary/findJoinWayType",
            "/dictionary/findLeaveType",
            "/dictionary/getTeamStatusType",

            "/team/searchTeamInfo",

            "/player/searchPlayerInfo",

//            "/credit/getTeamData",
//            "/credit/searchCreditBy"

//            "/user/getCurrentUserInfo",


            "/user/register",
            "/user/login"
    };
}
