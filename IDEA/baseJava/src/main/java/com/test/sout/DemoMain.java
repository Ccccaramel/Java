package com.test.sout;

public class DemoMain {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new DemoThread("A"));
        Thread thread2 = new Thread(new DemoThread("B"));

        thread1.start();
        thread2.start();

        System.out.println("3秒后对 System.out 加锁...");
        Thread.sleep(3000);
        synchronized (System.out){
            System.out.println("已对 System.out 加锁,3秒后释放");
            for (int i = 3; i > 0; i--) {
                System.out.println("倒计时:"+i+",A线程状态:"+thread1.getState()+",B线程状态:"+thread2.getState());
                Thread.sleep(1000);
            }
        }
        System.out.println("锁已释放...");
    }
}
