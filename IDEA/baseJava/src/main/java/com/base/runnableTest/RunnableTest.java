package com.base.runnableTest;

public class RunnableTest implements Runnable{
    private Thread t;
    private String threadName;

    public RunnableTest(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println("Running-"+threadName);
    }
}
