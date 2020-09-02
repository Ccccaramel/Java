package ding.com;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class RunnableDemo implements Runnable{
	private Thread t;
	private String threadName;
	RunnableDemo(String name){
		this.threadName=name;
		System.out.println("Creating "+this.threadName);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Running "+this.threadName);
		try {
			for(int i=4;i>0;i--) {
				System.out.println("Thrad:"+this.threadName+", "+i);
				Thread.sleep(50);
			}
		}catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("Thread "+this.threadName+" interrupted.");
		}
		System.out.println("Thread "+this.threadName+" exiting.");
	}
	public void start() {
		System.out.println("Starting "+this.threadName);
		if(this.t==null) {
			this.t=new Thread(this,this.threadName);
			this.t.start();
		}
	}
}
class ThreadDemo extends Thread{
	private Thread t;
	private String threadName;
	ThreadDemo(String name){
		this.threadName=name;
		System.out.println("Creating "+this.threadName);
	}
	public void run() {
		System.out.println("Running "+this.threadName);
		try {
			for(int i=4;i>0;i--) {
				System.out.println("Thread:"+this.threadName+","+i);
				Thread.sleep(50);
			}
		}catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("Thread "+this.threadName+" interrupted.");
		}
		System.out.println("Thread "+this.threadName+" exiting.");
	}
	public void start() {
		System.out.println("Starting "+this.threadName);
		if(this.t==null) {
			this.t=new Thread(this,this.threadName);
			this.t.start();
		}
	}
}
class CallableThreadDemo implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		for(;i<100;i++) {
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
		return i;
	}
}
public class JavaThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("--- Runnable ---");
		RunnableDemo r1=new RunnableDemo("Thread-1");
		r1.start();
		RunnableDemo r2=new RunnableDemo("Thread-2");
		r2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("--- Thread ---");
		ThreadDemo t3=new ThreadDemo("Thread-3");
		t3.start();
		ThreadDemo t4=new ThreadDemo("Thread-4");
		t4.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("--- Callable 和 Future ---");
		CallableThreadDemo c1=new CallableThreadDemo();
		FutureTask<Integer> f1=new FutureTask<Integer>(c1);
		for(int i=0;i<100;i++) {
			System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
			if(i==20) {
				new Thread(f1,"有返回值的线程").start();
			}
		}
		try {
			System.out.println("子线程的返回值:"+f1.get());
		}catch (ExecutionException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
