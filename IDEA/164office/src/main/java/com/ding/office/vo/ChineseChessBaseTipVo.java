package com.ding.office.vo;

import java.io.Serializable;

/**
 * 存储棋子信息
 */
public class ChineseChessBaseTipVo implements Cloneable,Serializable {
    /**
     * 0:棋子高亮 1:目标位置高亮 2:禁用
     */
    private Integer type;
    private Integer x;
    private Integer y;

    public ChineseChessBaseTipVo() {
    }

    /**
     * 高亮 tip
     * @param type 0:棋子高亮,1:目标位置高亮 2:禁用
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
