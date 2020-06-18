package ding.com;

import java.util.Enumeration;
import java.util.Hashtable;

public class JavaHashtable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 *  Hashtable �ڹ�ϣ���д洢��/ֵ��
		 * ��ʹ��һ����ϣ��,Ҫָ���������Ķ���,�Լ�Ҫ���ӵ��ü���ֵ
		 * Ȼ��ü�������ϣ����,���õ���ɢ���뱻�����洢�ڸñ���ֵ������
		 * ֧��ͬ��
		 * ���� Hashmap ��ʲô����?
		 *   (�� JavaDataStructure.java )
		 *  
		 *  Hashtable �ж���ķ���
		 *    void clear()                          ����ϣ�����,ʹ�䲻�����κμ�
		 *    Object clone()                        �����˹�ϣ���ǳ����
		 *    boolean contains(Object value)        ���Դ�ӳ������Ƿ������ָ��ֵ�����ļ�
		 *    boolean containsKey(Object key)       ����ָ�������Ƿ�Ϊ�˹�ϣ���еļ�
		 *    boolean containsValue(Object value)   �����  Hashtable ��һ��������ӳ�䵽��ֵ�򷵻� true
		 *    Enumeration elements()                ���ش˹�ϣ���е�ֵ��ö��
		 *    Object get(Object key)                ����ָ������ӳ�䵽��ֵ,�����ӳ�䲻�����˼���ӳ���򷵻� null
		 *    boolean isEmpty()                     ���Դ˹�ϣ���Ƿ�û�м�ӳ�䵽ֵ
		 *    Enumeration keys()                    ���ش˹�ϣ���еļ���ö��
		 *    Object put(Object key,Object value)   ��ָ�� key ӳ�䵽�˹�ϣ���е�ָ�� value
		 *    void rehash()                         ���Ӵ˹�ϣ������������ڲ�������������Ա���Ч���ɺͷ���Ԫ��
		 *    Object remove(Object key)             �ӹ�ϣ�����Ƴ��ü�������Ӧ��ֵ
		 *    int size()                            ���ش˱��еļ�������
		 *    String toString()                     ���ش� Hashtable ������ַ����ı�ʾ��ʽ
		 */
		Hashtable h=new Hashtable();
		Enumeration names;  // ö��
		String str;
		double bal;
		
		h.put("Zara", new Double(34.34));
		h.put("Mahnaz", new Double(88.66));
		h.put("Ayan", new Double(100.01));
		h.put("Qadir", new Double(-22.22));
		
		names=h.keys();  // ���ع�ϣ���еļ���ö��
		System.out.println("Hashtable:"+h);
		while(names.hasMoreElements()) {
			str=(String)names.nextElement();
			System.out.println(str+": "+h.get(str));
		}
	}

}
