package com.base.runnable;

public class Main {
    public static void main(String[] args) {
        RunnableImpl runnable1 = new RunnableImpl("Thread-1");
        runnable1.start();
        RunnableImpl runnable2 = new RunnableImpl("Thread-2");
        runnable2.start();
        RunnableImpl runnable3 = new RunnableImpl("Thread-3");
        runnable3.start();
    }
}
