package com.ding.hyld.constant;

public interface DictionaryCode {

    // 队员状态
    /**
     * 正式队员
     */
    Integer TEAM_MEMBER_STATUS_1 =100;
//    public static final Integer VALID_TEAM_MEMBER=100; // 正式队员

    // 战队状态
    /**
     * 正常
     */
    Integer TEAM_STATUS_1 = 0;

    /**
     * 正常 value
     */
    Integer TEAM_STATUS_1_value = 0;

    // 关联状态
    /**
     * 冻结中
     */
    Integer RELATION_STATUS_1 = 700;
    /**
     * 关联中
     */
    Integer RELATION_STATUS_2 = 701;
    /**
     * 已断联
     */
    Integer RELATION_STATUS_3 = 702;

    // 战队竞赛类型
    /**
     * 战队联赛周
     */
    Integer TEAM_COMPETITION_TYPE_1 = 600;
    /**
     * 战队任务周
     */
    Integer TEAM_COMPETITION_TYPE_2 = 601;

    /**
     * 待审核
     */
    Integer CHECK_STATUS_1 = 1400;

    /**
     * 审核中
     */
    Integer CHECK_STATUS_2 = 1401;

    /**
     * 审核通过
     */
    Integer CHECK_STATUS_3 = 1402;

    /**
     * 审核不通过
     */
    Integer CHECK_STATUS_4 = 1403;

    /**
     * 无需审核
     */
    Integer CHECK_STATUS_5 = 1404;

    /**
     * 队长
     */
    Integer PLAYER_POSITION_1 = 1200;

    /**
     * 副队长
     */
    Integer PLAYER_POSITION_2 = 1201;

    /**
     * 副队长 value
     */
    Integer PLAYER_POSITION_2_value = 1;

    /**
     * 队员
     */
    Integer PLAYER_POSITION_3 = 1202;

    // 言论状态
    /**
     * 正常
     */
    Integer SPEECH_STATUS_1 =1500;
    /**
     * 冻结
     */
    Integer SPEECH_STATUS_2 =1501;
    /**
     * 删除
     */
    Integer SPEECH_STATUS_3 =1502;

    // 反馈信息状态
    /**
     * 正常
     */
    Integer POINTER_STATUS_1 = 1600;
    /**
     * 删除
     */
    Integer POINTER_STATUS_2 = 1601;

    // 用户角色
    /**
     * 平台管理员
     */
    Integer USER_TYPE_1 = 500;
    /**
     * 普通用户
     */
    Integer USER_TYPE_2 = 501;

    // 用户状态
    /**
     * 正常
     */
    Integer USER_STATUS_1 = 400;
    /**
     * 冻结
     */
    Integer USER_STATUS_2 = 401;
    /**
     * 注销
     */
    Integer USER_STATUS_3 = 402;

    // 人气类型
    /**
     * 喜欢
     */
    Integer POPULARITY_TYPE_1 = 1900;
    /**
     * 不喜欢
     */
    Integer POPULARITY_TYPE_2 = 1901;
}
