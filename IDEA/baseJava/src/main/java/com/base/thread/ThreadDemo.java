package com.base.thread;

public class ThreadDemo extends Thread{

    public ThreadDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i=0;i<3;i++)
            System.out.println(getName()+">>>>"+i);
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadDemo t1=new ThreadDemo("线程A");
        ThreadDemo t2=new ThreadDemo("线程B");
        t1.start();
        t2.start();
    }
}
