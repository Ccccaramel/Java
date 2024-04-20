package com.ding.hyld.info;

public class ChessDetailInfo {
    private int piece;
    private ChessLocationInfo[] chessLocationInfos;

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public ChessLocationInfo[] getChessLocationInfos() {
        return chessLocationInfos;
    }

    public void setChessLocationInfos(ChessLocationInfo[] chessLocationInfos) {
        this.chessLocationInfos = chessLocationInfos;
    }
}
