package com.ding.office.vo;

/**
 * 存储棋子的位置信息
 */
public class ChineseChessBaseLocationVo {
    private Integer x;
    private Integer y;

    public ChineseChessBaseLocationVo(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public ChineseChessBaseLocationVo() {
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
