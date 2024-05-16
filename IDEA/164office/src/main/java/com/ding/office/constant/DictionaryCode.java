package com.ding.office.constant;

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
    Integer USER_ROLE_1 = 1;
    /**
     * 普通用户
     */
    Integer USER_ROLE_2 = 2;

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

    // 博客状态
    /**
     * 未激活
     */
//    Integer BLOG_STATUS_1 = 2100;
    /**
     * 正常
     */
    Integer BLOG_STATUS_2 = 2101;
    /**
     * 删除
     */
    Integer BLOG_STATUS_3 = 2103;

    // 博客访问权限
    /**
     * 公开
     */
    Integer BLOG_POWER_1 = 2300;
    /**
     * 仅作者可见
     */
    Integer BLOG_POWER_2 = 2301;

    // 博客分类
    /**
     * 技术科学类,默认类型
     */
    Integer BLOG_TYPE_1 = 2200;

    // 博客文件状态
    /**
     * 正常
     */
    Integer BLOG_FILE_STATUS_1 = 2400;

    // 账单状态
    /**
     * 正常
     */
    Integer BILL_STATUS_1 =3000;
    /**
     * 冻结
     */
    Integer BILL_STATUS_2 =3001;

    // music状态
    /**
     * 正常
     */
    Integer MUSIC_STATUS_1 =3100;
    /**
     * 删除
     */
    Integer MUSIC_STATUS_2 =3101;

    // musicLyric状态
    /**
     * 正常
     */
    Integer MUSIC_LYRIC_STATUS_1 =3200;
    /**
     * 删除
     */
    Integer MUSIC_LYRIC_STATUS_2 =3201;

    // 关系状态
    /**
     * 正常
     */
    Integer RELATIONSHIP_STATUS_1 =3300;

    /**
     * 待回应
     */
    Integer RELATIONSHIP_STATUS_2 =3301;

    /**
     * 解除
     */
    Integer RELATIONSHIP_STATUS_3 =3302;

    /**
     * 已撤销
     */
    Integer RELATIONSHIP_STATUS_4 =3303;

    /**
     * 已拒绝
     */
    Integer RELATIONSHIP_STATUS_5 =3304;

    // 关系类型
    /**
     * 好友
     */
    Integer RELATIONSHIP_TYPE_1 =3400;

    /**
     * 黑名单
     */
    Integer RELATIONSHIP_TYPE_2 =3401;

    // 聊天信息状态
    /**
     * 已读
     */
    Integer CHAT_STATUS_1 =3500;
    /**
     * 未读
     */
    Integer CHAT_STATUS_2 =3501;

    // 聊天信息类型
    /**
     * 私密
     */
    Integer CHAT_TYPE_1 =3600;
    /**
     * 公开
     */
    Integer CHAT_TYPE_2 =3601;

}
