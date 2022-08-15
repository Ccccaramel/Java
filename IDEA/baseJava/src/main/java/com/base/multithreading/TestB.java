package com.base.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程
 *
 * 当 JVM 中不存在运行中的非守护进程时,则 JVM 进程可正常退出
 */
public class TestB {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(()-> System.out.println("The JVM exit success!")));
        Thread thread=new Thread(()->{
            while (true){
                try{
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("I'm running ...");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        thread.setDaemon(true); // 将该线程设置为守护线程,且必须再 start 之前设置
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("The main thread ready to exit...");
    }
}
