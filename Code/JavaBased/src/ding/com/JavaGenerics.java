package ding.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
// ������
class Box<T>{
	private T t;
	public void add(T t) {
		this.t=t;
	}
	public T get() {
		return t;
	}
}
public class JavaGenerics {
	// ���ͷ���
	public static <E> void printArray(E[] inputArray) {
		for(E element:inputArray) {
			System.out.printf("%s \n",element);
			System.out.println(element.getClass().getTypeName());
		}
	}
	public static <T extends Comparable<T>> T maxinum(T x,T y,T z) {
		T max=x;
		if(y.compareTo(max)>0) {
			max=y;
		}
		if(z.compareTo(max)>0) {
			max=z;
		}
		return max;
	}
	
	// ����ͨ���
	public static void getData(List<?> data) {
		System.out.println("data:"+data.get(0));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * �����ṩ�˱���ʱ���Ͱ�ȫ������,�û����������Ա�ڱ���ʱ��⵽�Ƿ�������
		 * ���͵ı����ǲ���������,Ҳ����˵���������������ͱ�ָ��Ϊһ������
		 * 
		 */
		Integer[] intArray= {1,2,3,4,5};
		Double[] doubleArray= {1.1,1.2,1.3,1.4,1.5};
		Character[] charArray= {'H','A','C','E','J'};
		System.out.println("��������Ԫ��Ϊ:");
		printArray(intArray);
		System.out.println("˫����������Ԫ��Ϊ:");
		printArray(doubleArray);
		System.out.println("�ַ�������Ԫ��Ϊ:");
		printArray(charArray);
		
		// ���ͷ���
		System.out.println("---���ͷ���---");
		System.out.printf("%d %d %d ��������Ϊ %d\n",3,4,5,maxinum(3, 4, 5));
		System.out.printf("%.1f %.1f %.1f ��������Ϊ %.1f\n",5.3, 2.4, 5.1,maxinum(5.3, 2.4, 5.1));
		System.out.printf("%s %s %s ��������Ϊ %s\n","r", "a", "g",maxinum("r", "a", "g"));
		
		// ������
		System.out.println("---������---");
		Box<Integer> i1=new Box<Integer>();
		Box<String> s1=new Box<String>();
		i1.add(new Integer(10));
		s1.add(new String("Java"));
		System.out.println("���� :"+i1.get());
		System.out.println("�ַ���:"+s1.get());
		
		/**
		 *  ����ͨ���
		 *    һ����ʹ�� ? �����������Ͳ���,���� List<?>
		 *    ���߼��� List<String>,List<Integer> ������ List<��������ʵ��> �ĸ���
		 */
		System.out.println("---����ͨ���---");
		List<String> name=new ArrayList<String>();
		List<Integer> age=new ArrayList<Integer>();
		List<Number> number=new ArrayList<Number>();
		name.add("icon");
		age.add(18);
		number.add(314);
		getData(name);
		getData(age);
		getData(number);
	}

}
