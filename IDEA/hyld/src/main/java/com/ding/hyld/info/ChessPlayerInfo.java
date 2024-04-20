package com.ding.hyld.info;

import javax.websocket.Session;

/**
 * 棋手信息
 */
public class ChessPlayerInfo {
    private Session session;  // session 会话信息
    private Integer user;  // 用户 id
    private Integer skill=0;  // 拥有的技能
    private ChessDetailInfo[] chessDetailInfos;  // 拥有的棋子信息

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getSkill() {
        return skill;
    }

    public void setSkill(Integer skill) {
        this.skill = skill;
    }

    public ChessDetailInfo[] getChessDetailInfos() {
        return chessDetailInfos;
    }

    public void setChessDetailInfos(ChessDetailInfo[] chessDetailInfos) {
        this.chessDetailInfos = chessDetailInfos;
    }
}
