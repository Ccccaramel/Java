package com.ding.hyld.vo;

import java.util.ArrayList;

/**
 * 存储棋子信息
 */
public class ChineseChessBaseTipVo {
    private Integer type;  // 0:棋子高亮,1:目标位置高亮
    private Integer x;  // x
    private Integer y;  // y

    public ChineseChessBaseTipVo() {
    }

    /**
     * 高亮 tip
     * @param type 0:棋子高亮,1:目标位置高亮
     * @param x X轴
     * @param y Y轴
     */
    public ChineseChessBaseTipVo(Integer type, Integer x, Integer y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
