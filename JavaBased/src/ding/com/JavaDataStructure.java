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
		 * ���ݽṹ
		 *   �� Java �е����ݽṹ��Ҫ����һ�¼��ֽӿں���
		 *     1.ö��
		 *       ö��( Enumeration )�ӿ���Ȼ�����������ݽṹ
		 *       �������������ݽṹ�ķ�����Ӧ�úܹ�
		 *       ö�ٽӿڶ�����һ�ִ����ݽṹ��ȡ������Ԫ�صķ�ʽ
		 *     2.λ����
		 *       λ����( BitSet )ʵ����һ����Ե������ú������λ���־
		 *       �����ڴ���һ�鲼��ֵʱ�ǳ�����,��ֻ���ÿ��ֵ��ֵһ"λ"
		 *       Ȼ���λ�����ʵ������û����,�Ϳ��ԶԲ���ֵ���в���
		 *     3.����
		 *       ����( Vector ) ��ʹ�ͳ����ǳ�����,���� Vector �Ĵ�С�ܸ�����Ҫ��̬�ı仯
		 *       ������һ��, Vector �����Ԫ��Ҳ��ͨ����������
		 *       ʹ�� Vector ������Ҫ�ĺô����ڴ�����ʱ�򲻱ظ�����ָ����С
		 *     4.ջ
		 *       ջ( Stack )ʵ����һ������ȳ�( LIFO )�����ݽṹ
		 *     5.�ֵ�
		 *       �ֵ�( Dictionary )����һ��������,�������˼�ӳ�䵽ֵ�����ݽṹ
		 *       ������ͨ���ض��ļ������������������������ݵ�ʱ���Ӧ�ÿ���ʹ�� Dictionary
		 *       ���� Dictionary ���ǳ�����,������ֻ�ṩ�˼�ӳ�䵽ֵ�����ݽṹ,��û���ṩ�ض���ʵ��
		 *     6.��ϣ��
		 *       ���� HashMap ����,����֧��ͬ��
		 *     7.����
		 *       ����( Properties )�̳��� Hashtable.Properties ���ʾ��һ���־õ����Լ�
		 *       �����б���ÿ�����Լ����Ӧֵ����һ���ַ���
		 *       ����洢?
		 *     8.���Ͽ��
		 *       (��� JavaCollectionClass.java )
		 */
		System.out.println("---ö��---");
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
		
		System.out.println("---λ����---");
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
		b2.and(b1);  // ��
		System.out.println("bits2 AND bits1:"+b2);
		b2.or(b1);  // ��
		System.out.println("bits2 or bits1:"+b2);
		b2.xor(b1);  // ���
		System.out.println("bits2 xor bits1:"+b2);
		
		System.out.println("---����---");
		Vector v2=new Vector(3,2);  // ��ʼ����Ϊ3,ÿ�ε���2
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
		
		System.out.println("---ջ---");
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
		
		System.out.println("---�ֵ�---");
		/**
		 *  dictionary ���Ѿ���ʱ�˽���ʹ�� Map ����ȡ��ֵ�ԵĴ洢����
		 *  (�� JavaCollectionCalss.java )
		 */
		
		System.out.println("---��ϣ��---");
		/**
		 *  (�� JavaHashtable.java )
		 */		
		
		System.out.println("---����---");
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
