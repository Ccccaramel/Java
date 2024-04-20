package com.ding.hyld.info;

/**
 * 房间属性
 */
public class ChineseChessRoomInfo {
    private String id;  // id
    private String name;  // 名称
    private String password;  // 密码
    private ChessPlayerInfo rPlayer;  // 红方(房主)
    private ChessPlayerInfo bPlayer;  // 黑方
    private Integer[][] table;  // 棋盘
    private boolean action;  // true:红方行动 false:黑方行动

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ChessPlayerInfo getrPlayer() {
        return rPlayer;
    }

    public void setrPlayer(ChessPlayerInfo rPlayer) {
        this.rPlayer = rPlayer;
    }

    public ChessPlayerInfo getbPlayer() {
        return bPlayer;
    }

    public void setbPlayer(ChessPlayerInfo bPlayer) {
        this.bPlayer = bPlayer;
    }

    public Integer[][] getTable() {
        return table;
    }

    public void setTable(Integer[][] table) {
        this.table = table;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }
}
