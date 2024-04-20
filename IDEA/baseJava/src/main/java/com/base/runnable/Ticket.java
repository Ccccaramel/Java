package com.base.runnable;

public class Ticket implements Runnable{
    private Integer tickets=100;
    private boolean enough=true;

    @Override
    public synchronized void run() {
        while (enough){
            if(tickets>0){
                System.out.println(Thread.currentThread().getName() +" >>> 拿到了第"+this.tickets+ "张票");
                this.tickets--;
                if(this.tickets==0){
                    this.enough=false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket,"A").start();
        new Thread(ticket,"B").start();
        new Thread(ticket,"C").start();
    }
}
