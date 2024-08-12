package com.ding.office.thread;

import com.ding.office.vo.ChineseChessRoomVo;
import com.ding.office.webSocket.ChineseChessEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 中国象棋
 * 对战时每秒向双方更新思考时间
 */
@Slf4j
public class ChineseChessRunnable implements Runnable{
    private Thread thread;
    private String threadName;
    private ChineseChessRoomVo vo;

    public ChineseChessRunnable(String threadName){
        this.threadName=threadName;
    }

    @Override
    public void run() {
        log.info("Running "+this.threadName);
        while (true){
            if(vo!=null&&vo.getStatus()==3){  // 房间存在且在对战中
                if(Objects.equals(vo.getCountdown(),0)){  // 倒计时结束
                    ChineseChessEndpoint.timeNotification(vo.getId(),true);
                    break;
                }
                // 计时器减1并通知双方
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                vo.decrementCountdown();
                ChineseChessEndpoint.timeNotification(vo.getId(),false);  // 计时刷新
            }
            else {
                log.info(this.threadName+" 非对战中!");
                break;
            }
        }


    }

    public void start(ChineseChessRoomVo vo){
        log.info("Starting "+this.threadName);
        if(this.thread==null){
            this.thread=new Thread(this,this.threadName);
            this.vo=vo;
            this.thread.start();
        }
    }
}
