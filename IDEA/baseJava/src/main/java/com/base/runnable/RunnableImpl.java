package com.base.runnable;

public class RunnableImpl implements Runnable {
    private Thread thread;
    private String threadName;

    RunnableImpl(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        System.out.println("Running " + this.threadName);
        try{
            for (int i=3;i>0;i--){
                System.out.println("Thread " + this.threadName + ", " + i);
                Thread.sleep(50);
            }
        }catch(InterruptedException e){
            System.out.println("Thread " + this.threadName + "interrupted.");
        }
    }

    public void start(){
        System.out.println("Starting " + this.threadName);
        if(this.thread==null){
            this.thread = new Thread(this,this.threadName);  // 重点
            this.thread.start();
        }
    }

    public static void main(String[] args) {
        RunnableImpl runnable1 = new RunnableImpl("Thread-1");
        RunnableImpl runnable2 = new RunnableImpl("Thread-2");
        RunnableImpl runnable3 = new RunnableImpl("Thread-3");
        runnable1.start();
        runnable2.start();
        runnable3.start();
    }
}
