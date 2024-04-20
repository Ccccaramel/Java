package com.base.thread;

public class JoinDemo implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        JoinDemo joinDemo = new JoinDemo();
        Thread thread = new Thread(joinDemo, "a");
        thread.start();
        thread.getState();
        for (int i = 0; i < 5; i++) {
            if(i==3){
                thread.join();
            }
            System.out.println("main running! >" + i);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName()+" thread running! > "+ Math.random());
        }
    }
}
