package ding.com;

import java.util.Scanner;

public class JavaScanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("--- next() ---");
		Scanner sc1=new Scanner(System.in);
		System.out.println("next 方式接收:");
		if(sc1.hasNext()) {
			String s1=sc1.next();
			System.out.println("输入的数据为:"+s1);
		}
//		sc1.close();
		
		System.out.println("--- nextLine() ---");
//		Scanner sc2=new Scanner(System.in);
		int i1=0;
		float f1=0.0f;
		System.out.println("输入整数:");
		if(sc1.hasNextInt()) {
			i1=sc1.nextInt();
			System.out.println("整数数据为:"+i1);
		}else {
			System.out.println("输入的不是整数!");
		}
		System.out.println("输入小数:");
		if(sc1.hasNextFloat()) {
			f1=sc1.nextFloat();
			System.out.println("小数数据为:"+f1);
		}else {
			System.out.println("输入的不是小数!");
		}
		
		double sum=0;
		int m=0;
		while(sc1.hasNextDouble()) {
			double x=sc1.nextDouble();
			m+=1;
			sum+=x;
		}
		System.out.println(m+"个数的和为"+sum+",个数的平均值为"+(sum/m));
		
		sc1.close();
	}

}
