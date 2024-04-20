package com.base.thread;

// 线程礼让
public class YieldDemo implements Runnable{
    public static void main(String[] args) {
        YieldDemo yieldDemo = new YieldDemo();
        new Thread(yieldDemo,"a").start();
        new Thread(yieldDemo,"b").start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" step1");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+" step2");
    }
}
