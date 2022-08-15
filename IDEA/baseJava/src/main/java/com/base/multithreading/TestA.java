package com.base.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * 非守护线程
 *
 * 当 JVM 中不存在运行中的非守护进程时,则 JVM 进程可正常退出
 */
public class TestA {
    public static void main(String[] args) {
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

        thread.start();
        System.out.println("The main thread ready to exit...");
    }
}
