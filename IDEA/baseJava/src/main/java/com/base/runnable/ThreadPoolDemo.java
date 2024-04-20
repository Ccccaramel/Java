package com.base.runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.shutdownNow();
    }
}
class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println("thread name:"+Thread.currentThread().getName());
    }
}

