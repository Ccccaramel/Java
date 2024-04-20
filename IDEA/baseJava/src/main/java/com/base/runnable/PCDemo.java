package com.base.runnable;

public class PCDemo {
    public static void main(String[] args) {
        Store store=new Store();
        new Thread(new Productor(store)).start();
        new Thread(new Customer(store)).start();
    }
}

class Productor implements Runnable{  // 生产者
    Store store;
    public Productor(Store store) {
        this.store = store;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            store.push(new Product(i));
        }
    }
}

class Customer implements Runnable{  // 消费者
    Store store;
    public Customer(Store store) {
        this.store = store;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            store.pop(i);
        }
    }
}

class Product{  // 产品
    private int id;
    public Product(int id) {
        this.id = id;
    }
}

class Store{  // 仓库,缓冲区
    Product[] products=new Product[100];
    private int index = 0;  // 仓库的产品数量

    public synchronized void push(Product product){  // 产品放入仓库
        if(index==100){  // 仓库满了,放不下了
            System.out.println("仓库满了,停止生产");
            try {
                this.wait();  // 通知消费者消费,生产者等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products[index++]=product;
        System.out.println("产品放入仓库成功,当前仓库有"+index+"个产品");
        this.notifyAll();  // 可以消费了
    }

    public synchronized Product pop(int i) {  // 从仓库中消费产品
        if (index == 0) {  // 仓库中没有产品
            System.out.println("仓库中没有产品,停止消费");
            try {
                this.wait();  // 等待生产者生产,消费者等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Product product = products[--index];
        products[index] = null;
        System.out.println("已消费第"+i+"个产品");
        this.notifyAll();  // 可以生产了
        return product;
    }
}