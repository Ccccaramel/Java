package ding.com;

import java.util.Enumeration;
import java.util.Hashtable;

public class JavaHashtable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 *  Hashtable 在哈希表中存储键/值对
		 * 当使用一个哈希表,要指定用作键的对象,以及要链接到该键的值
		 * 然后该键经过哈希处理,所得到的散列码被用作存储在该表中值的索引
		 * 支持同步
		 * 它与 Hashmap 有什么区别?
		 *   (见 JavaDataStructure.java )
		 *  
		 *  Hashtable 中定义的方法
		 *    void clear()                          将哈希表清空,使其不包含任何键
		 *    Object clone()                        创建此哈希表的浅表副本
		 *    boolean contains(Object value)        测试此映射表中是否存在与指定值关联的键
		 *    boolean containsKey(Object key)       测试指定对象是否为此哈希表中的键
		 *    boolean containsValue(Object value)   如果此  Hashtable 将一个或多个键映射到此值则返回 true
		 *    Enumeration elements()                返回此哈希表中的值的枚举
		 *    Object get(Object key)                返回指定键所映射到的值,如果此映射不包含此键的映射则返回 null
		 *    boolean isEmpty()                     测试此哈希表是否没有键映射到值
		 *    Enumeration keys()                    返回此哈希表中的键的枚举
		 *    Object put(Object key,Object value)   将指定 key 映射到此哈希表中的指定 value
		 *    void rehash()                         增加此哈希表的容量并在内部对其进行重组以便有效容纳和访问元素
		 *    Object remove(Object key)             从哈希表中移除该键及其相应的值
		 *    int size()                            返回此表中的键的数量
		 *    String toString()                     返回此 Hashtable 对象的字符串的表示形式
		 */
		Hashtable h=new Hashtable();
		Enumeration names;  // 枚举
		String str;
		double bal;
		
		h.put("Zara", new Double(34.34));
		h.put("Mahnaz", new Double(88.66));
		h.put("Ayan", new Double(100.01));
		h.put("Qadir", new Double(-22.22));
		
		names=h.keys();  // 返回哈希表中的键的枚举
		System.out.println("Hashtable:"+h);
		while(names.hasMoreElements()) {
			str=(String)names.nextElement();
			System.out.println(str+": "+h.get(str));
		}
	}

}
