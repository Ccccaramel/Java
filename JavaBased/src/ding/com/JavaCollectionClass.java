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
		 * ���Ͽ�ܱ���Ƴ�Ҫ�������¼���Ŀ��
		 *   ������
		 *   ����ͬ���͵ļ���
		 *   ��չ����Ӧ��
		 * ���м��Ͽ�ܶ�������������
		 *   �ӿ�
		 *   ʵ��(��)
		 *   �㷨
		 *   
		 *  Set �� List ������
		 *   1. Set �ӿ�ʵ���洢�������Ҳ��ظ���, List ��������ظ���
		 *   2. Set ����Ч�ʵ���,ɾ���Ͳ���Ч�ʸ�,�����ɾ����������Ԫ��λ�øı�
		 *   3. List ����������,���Զ�̬����,����ʵ�ʴ洢�����ݵĳ����Զ����� List �ĳ���
		 *     ����Ԫ��Ч�ʸ�,����ɾ��Ч�ʵ�,����������Ԫ��λ�øı�
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
		System.out.println("---ʹ�� For-Each ���� List ͨ�� Map.keySet---");
		for(String s:l1) {  //  ���� key �� value
			System.out.println(s);
		}
		System.out.println("---�������Ϊ������ص����ݽ��б���---");
		String[] s1=new String[l1.size()];
		l1.toArray(s1);
		for(int i=0;i<s1.length;i++) {
			System.out.println(s1[i]);
		}
		System.out.println("---ʹ�õ�����������ر���---");
		Iterator<String> i1=l1.iterator();
		while(i1.hasNext()) {
			System.out.println(i1.next());
		}
		/**
		 *  Map �ӿ�
		 *    Map �ӿ��м���ֵһһӳ��, ����ͨ��������ȡֵ
		 */
		System.out.println("***** Map *****");
		Map<String,String> m1=new HashMap<String,String>();
		m1.put("Zara", "8");
		m1.put("Mahnaz", "31");
		m1.put("Ayan", "12");
		m1.put("Daisy", "14");
		System.out.println("Map:"+m1);
		System.out.println("---����ȡֵ---");
		for(String key:m1.keySet()) {
			System.out.println("key="+key+",value="+m1.get(key));
		}
		System.out.println("---ͨ�� Map.entrySet ʹ�� iterator ���� key �� value ---");
		Iterator<Map.Entry<String, String>> i2=m1.entrySet().iterator();
		while(i2.hasNext()) {
			Map.Entry<String, String> e1=i2.next();
			System.out.println("key="+e1.getKey()+",value="+e1.getValue());
		}
		System.out.println("---ͨ�� Map.entrySet ���� key �� value (�����ڴ�����)---");
		for(Map.Entry<String, String> e2:m1.entrySet()) {
			System.out.println("key="+e2.getKey()+",value="+e2.getValue());
		}
		System.out.println("---ͨ�� Map.values �������е� value ,�����ܱ��� key ---");
		for(String s2:m1.values()) {
			System.out.println("value="+s2);
		}
	}

}
