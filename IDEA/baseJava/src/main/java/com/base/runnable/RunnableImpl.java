package com.base.runnable;

public class RunnableImpl implements Runnable {
    private Thread thread;
    private String threadName;

    RunnableImpl(String name) {
        this.threadName = name;
        System.out.println("Creating " + this.threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + this.threadName);
        try{
            for (int i=10;i>0;i--){
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
            this.thread = new Thread(this,this.threadName);
            this.thread.start();
        }
    }
}
