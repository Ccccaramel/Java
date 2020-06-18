package ding.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JavaCollectionClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 集合框架被设计成要满足以下几个目标
		 *   高性能
		 *   允许不同类型的集合
		 *   扩展和适应简单
		 * 所有集合框架都包含如下内容
		 *   接口
		 *   实现(类)
		 *   算法
		 *   
		 *  Set 与 List 的区别
		 *   1. Set 接口实例存储是无序且不重复的, List 是有序可重复的
		 *   2. Set 检索效率低下,删除和插入效率高,插入和删除不会引起元素位置改变
		 *   3. List 和数组类似,可以动态增长,根据实际存储的数据的长度自动增长 List 的长度
		 *     查找元素效率高,插入删除效率低,会引起其它元素位置改变
		 * 
		 */
		/**
		 *  ArrayList
		 */
		System.out.println("***** ArrayList *****");
		List<String> l1=new ArrayList<String>();
		l1.add("hello");
		l1.add("World");
		l1.add("Java");
		System.out.println("---使用 For-Each 遍历 List 通过 Map.keySet---");
		for(String s:l1) {  //  遍历 key 和 value
			System.out.println(s);
		}
		System.out.println("---把链表变为数组相关的内容进行遍历---");
		String[] s1=new String[l1.size()];
		l1.toArray(s1);
		for(int i=0;i<s1.length;i++) {
			System.out.println(s1[i]);
		}
		System.out.println("---使用迭代器进行相关遍历---");
		Iterator<String> i1=l1.iterator();
		while(i1.hasNext()) {
			System.out.println(i1.next());
		}
		/**
		 *  Map 接口
		 *    Map 接口中键和值一一映射, 可以通过键来获取值
		 */
		System.out.println("***** Map *****");
		Map<String,String> m1=new HashMap<String,String>();
		m1.put("Zara", "8");
		m1.put("Mahnaz", "31");
		m1.put("Ayan", "12");
		m1.put("Daisy", "14");
		System.out.println("Map:"+m1);
		System.out.println("---二次取值---");
		for(String key:m1.keySet()) {
			System.out.println("key="+key+",value="+m1.get(key));
		}
		System.out.println("---通过 Map.entrySet 使用 iterator 遍历 key 和 value ---");
		Iterator<Map.Entry<String, String>> i2=m1.entrySet().iterator();
		while(i2.hasNext()) {
			Map.Entry<String, String> e1=i2.next();
			System.out.println("key="+e1.getKey()+",value="+e1.getValue());
		}
		System.out.println("---通过 Map.entrySet 遍历 key 和 value (适用于大容量)---");
		for(Map.Entry<String, String> e2:m1.entrySet()) {
			System.out.println("key="+e2.getKey()+",value="+e2.getValue());
		}
		System.out.println("---通过 Map.values 遍历所有的 value ,但不能遍历 key ---");
		for(String s2:m1.values()) {
			System.out.println("value="+s2);
		}
	}

}
