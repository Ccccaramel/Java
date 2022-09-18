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
     * 队长
     */
    Integer PLAYER_POSITION_1 = 1200;

    /**
     * 副队长
     */
    Integer PLAYER_POSITION_2 = 1201;
}
