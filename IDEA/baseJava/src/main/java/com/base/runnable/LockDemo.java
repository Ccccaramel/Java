package com.base.runnable;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) {
        A a= new A();
        new Thread(a,"1").start();
        new Thread(a,"2").start();
        new Thread(a,"3").start();
    }
}

class A implements Runnable{
    private Integer i = 10;
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            lock.lock();  // 加锁
            if(i>0){
                System.out.println(Thread.currentThread().getName()+" >>> " +i--);
            }else {
                lock.unlock();  // 释放锁
                break;
            }
            lock.unlock();  // 释放锁
        }
    }
}