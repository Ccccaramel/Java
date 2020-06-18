package ding.com;

import java.util.BitSet;
import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

public class JavaDataStructure {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 数据结构
		 *   在 Java 中的数据结构主要包括一下几种接口和类
		 *     1.枚举
		 *       枚举( Enumeration )接口虽然本身不属于数据结构
		 *       但它在其它数据结构的范畴里应用很广
		 *       枚举接口定义了一种从数据结构中取回连续元素的方式
		 *     2.位集合
		 *       位集合( BitSet )实现了一组可以单独设置和清除的位或标志
		 *       该类在处理一组布尔值时非常有用,你只需给每个值赋值一"位"
		 *       然后对位进行适当的设置或清除,就可以对布尔值进行操作
		 *     3.向量
		 *       向量( Vector ) 类和传统数组非常相似,但是 Vector 的大小能根据需要动态的变化
		 *       和数组一样, Vector 对象的元素也能通过索引访问
		 *       使用 Vector 类最主要的好处是在创建的时候不必给对象指定大小
		 *     4.栈
		 *       栈( Stack )实现了一个后进先出( LIFO )的数据结构
		 *     5.字典
		 *       字典( Dictionary )类是一个抽象类,它定义了键映射到值的数据结构
		 *       当你想通过特定的键而不是整数索引来访问数据的时候就应该考虑使用 Dictionary
		 *       由于 Dictionary 类是抽象类,所以它只提供了键映射到值的数据结构,而没有提供特定的实现
		 *     6.哈希表
		 *       它和 HashMap 相似,但它支持同步
		 *     7.属性
		 *       属性( Properties )继承于 Hashtable.Properties 类表示了一个持久的属性集
		 *       属性列表中每个键以及其对应值都是一个字符串
		 *       无序存储?
		 *     8.集合框架
		 *       (详见 JavaCollectionClass.java )
		 */
		System.out.println("---枚举---");
		Enumeration<String> e1;
		Vector<String> v1=new Vector<String>();
		v1.add("Sunday");
		v1.add("Monday");
		v1.add("Tuesday");
		v1.add("Wednesday");
		v1.add("Thursday");
		v1.add("Friday");
		v1.add("Saturday");
		e1=v1.elements();
		while(e1.hasMoreElements()) {
			System.out.println(e1.nextElement());
		}
		
		System.out.println("---位集合---");
		BitSet b1=new BitSet(16);
		BitSet b2=new BitSet(16);
		for(int i=0;i<16;i++) {
			if((i%2)==0) {
				b1.set(i);
			}
			if((i%5)!=0) {
				b2.set(i);
			}
		}
		System.out.println("Initial pattern in bits1:"+b1);
		System.out.println("Initial pattern in birs2:"+b2);
		b2.and(b1);  // 与
		System.out.println("bits2 AND bits1:"+b2);
		b2.or(b1);  // 或
		System.out.println("bits2 or bits1:"+b2);
		b2.xor(b1);  // 异或
		System.out.println("bits2 xor bits1:"+b2);
		
		System.out.println("---向量---");
		Vector v2=new Vector(3,2);  // 初始容量为3,每次递增2
		System.out.println("Initial size:"+v2.size());
		System.out.println("Initial capacity:"+v2.capacity());
		v2.addElement(new Integer(1));
		v2.addElement(new Integer(2));
		v2.addElement(new Integer(3));
		v2.addElement(new Integer(4));
		System.out.println("Capacity after four additions:"+v2.capacity());
		v2.addElement(new Integer(5));
		v2.addElement(new Integer(6));
		System.out.println("Capacity after four additions:"+v2.capacity());
		v2.addElement(new Integer(7));
		System.out.println("Capacity after four additions:"+v2.capacity());
		System.out.println("First element:"+(Integer)v2.firstElement());
		System.out.println("Last element:"+(Integer)v2.lastElement());
		
		System.out.println("---栈---");
		Stack<Integer> s1=new Stack<Integer>();
		System.out.println("stack:"+s1);
		s1.push(new Integer(23));
		s1.push(new Integer(12));
		s1.push(new Integer(78));
		System.out.println("stack:"+s1);
		System.out.println((Integer)s1.pop());
		System.out.println((Integer)s1.pop());
		System.out.println((Integer)s1.pop());
		try {
			System.out.println((Integer)s1.pop());
		}catch(EmptyStackException e){
			System.out.println("empty stack.");
		}
		
		System.out.println("---字典---");
		/**
		 *  dictionary 类已经过时了建议使用 Map 来获取键值对的存储功能
		 *  (见 JavaCollectionCalss.java )
		 */
		
		System.out.println("---哈希表---");
		/**
		 *  (见 JavaHashtable.java )
		 */		
		
		System.out.println("---属性---");
		Properties p1=new Properties();
		Set s2;
		String s3;
		p1.put("Illinois", "Springfiled");
		p1.put("Missouri", "Jefferson City");
		p1.put("Washington", "Olympia");
		p1.put("California", "Sacramento");
		p1.put("Indiana", "Indianapolis");
		s2=p1.keySet();
		Iterator i1=s2.iterator();
		while(i1.hasNext()) {
			s3=(String)i1.next();
			System.out.println("The capital of "+s3+" is"+p1.getProperty(s3)+".");
		}
		s3=p1.getProperty("Florida","Not Found");
		System.out.println("The capital of Florida is "+s3+".");
	}

}
