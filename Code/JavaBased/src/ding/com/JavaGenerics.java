package ding.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
// 泛型类
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
	// 泛型方法
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
	
	// 类型通配符
	public static void getData(List<?> data) {
		System.out.println("data:"+data.get(0));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 泛型提供了编译时类型安全检测机制,该机制允许程序员在编译时检测到非法的类型
		 * 泛型的本质是参数化类型,也就是说所操作的数据类型被指定为一个参数
		 * 
		 */
		Integer[] intArray= {1,2,3,4,5};
		Double[] doubleArray= {1.1,1.2,1.3,1.4,1.5};
		Character[] charArray= {'H','A','C','E','J'};
		System.out.println("整型数组元素为:");
		printArray(intArray);
		System.out.println("双精度型数组元素为:");
		printArray(doubleArray);
		System.out.println("字符型数组元素为:");
		printArray(charArray);
		
		// 泛型方法
		System.out.println("---泛型方法---");
		System.out.printf("%d %d %d 中最大的数为 %d\n",3,4,5,maxinum(3, 4, 5));
		System.out.printf("%.1f %.1f %.1f 中最大的数为 %.1f\n",5.3, 2.4, 5.1,maxinum(5.3, 2.4, 5.1));
		System.out.printf("%s %s %s 中最大的数为 %s\n","r", "a", "g",maxinum("r", "a", "g"));
		
		// 泛型类
		System.out.println("---泛型类---");
		Box<Integer> i1=new Box<Integer>();
		Box<String> s1=new Box<String>();
		i1.add(new Integer(10));
		s1.add(new String("Java"));
		System.out.println("整型 :"+i1.get());
		System.out.println("字符串:"+s1.get());
		
		/**
		 *  类型通配符
		 *    一般是使用 ? 代替具体的类型参数,例如 List<?>
		 *    在逻辑上 List<String>,List<Integer> 等所有 List<具体类型实参> 的父类
		 */
		System.out.println("---类型通配符---");
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
