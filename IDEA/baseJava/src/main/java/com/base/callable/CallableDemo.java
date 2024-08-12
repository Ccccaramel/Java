package com.base.callable;

import java.util.concurrent.*;

public class CallableDemo implements Callable<Integer> {
    private String name;
    private Integer num;

    public CallableDemo(String name,Integer num) {
        this.name = name;
        this.num = num;
    }

    @Override
    public Integer call() throws InterruptedException {
        int sum = 0;
        for (int i=0;i<=this.num;i++){
            sum+=i;
            Thread.sleep(10);
        }
        System.out.println(this.name+ " >>> "+sum);
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo c1 = new CallableDemo("A",4);
        CallableDemo c2 = new CallableDemo("B",3);
        CallableDemo c3 = new CallableDemo("C",2);

        // 将线程提交并执行
        FutureTask<Integer> f1= new FutureTask<>(c1);
        FutureTask<Integer> f2= new FutureTask<>(c2);
        FutureTask<Integer> f3= new FutureTask<>(c3);

        new Thread(f1).start();
        new Thread(f2).start();
        new Thread(f3).start();

        // 获取结果
        System.out.println("A:"+f1.get());
        System.out.println("B:"+f2.get());
        System.out.println("C:"+f3.get());

        System.out.println("-end-");
    }
}
