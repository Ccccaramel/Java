package com.ding.office.vo;

import java.util.ArrayList;

/**
 * 存储棋子信息
 */
public class ChineseChessBaseVo {
    private boolean roomOwner;
    private Integer piece;  // 1:兵/卒 2:炮/砲 3:车 4:马 5:相/象 6:士/仕 7:帅/将
    private ArrayList<ChineseChessBaseLocationVo> location;  // 棋子的位置

    public boolean isRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(boolean roomOwner) {
        this.roomOwner = roomOwner;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    public ArrayList<ChineseChessBaseLocationVo> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<ChineseChessBaseLocationVo> location) {
        this.location = location;
    }
}
