package ding.com;

import java.util.Scanner;

public class JavaScanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("--- next() ---");
		Scanner sc1=new Scanner(System.in);
		System.out.println("next ��ʽ����:");
		if(sc1.hasNext()) {
			String s1=sc1.next();
			System.out.println("���������Ϊ:"+s1);
		}
//		sc1.close();
		
		System.out.println("--- nextLine() ---");
//		Scanner sc2=new Scanner(System.in);
		int i1=0;
		float f1=0.0f;
		System.out.println("��������:");
		if(sc1.hasNextInt()) {
			i1=sc1.nextInt();
			System.out.println("��������Ϊ:"+i1);
		}else {
			System.out.println("����Ĳ�������!");
		}
		System.out.println("����С��:");
		if(sc1.hasNextFloat()) {
			f1=sc1.nextFloat();
			System.out.println("С������Ϊ:"+f1);
		}else {
			System.out.println("����Ĳ���С��!");
		}
		
		double sum=0;
		int m=0;
		while(sc1.hasNextDouble()) {
			double x=sc1.nextDouble();
			m+=1;
			sum+=x;
		}
		System.out.println(m+"�����ĺ�Ϊ"+sum+",������ƽ��ֵΪ"+(sum/m));
		
		sc1.close();
	}

}
