package com.base.multithreading;

public class RunnableA implements Runnable{
    private Thread t;
    private String threadName;

    public RunnableA(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating "+threadName);
    }

    @Override
    public void run() {
        System.out.println("Running "+threadName);
        try{
            for (int i=4;i>0;i--){
                System.out.println("Thread:"+threadName+","+i);
                Thread.sleep(50);
            }
        }catch (InterruptedException e){
            System.out.println("Thread"+threadName+"interrupted.");
        }
        System.out.println("Thread "+threadName+" exiting.");
    }

    public void start(){
        System.out.println("Starting "+threadName);
        if(t==null){
            t=new Thread(this,threadName);
            t.start(); // 使该线程开始执行, Java 虚拟机调用该线程的 run 方法
        }
    }

    public static void main(String[] args){
        System.out.println("***Runnable***");
        RunnableA r1=new RunnableA("Thread-1");
        r1.start();
        RunnableA r2=new RunnableA("Thread-2");
        r2.start();
    }
}
