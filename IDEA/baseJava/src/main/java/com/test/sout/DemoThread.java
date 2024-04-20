package com.test.sout;

public class DemoThread implements Runnable{
    private String name;
    public DemoThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(name+"线程运行中...");
            try {
                Thread.sleep(1500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
