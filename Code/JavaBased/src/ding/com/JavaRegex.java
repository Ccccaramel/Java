package ding.com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegex {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 *  Pattern ��
		 *    Pattern ������һ��������ʽ�ı����ʾ, Pattern ��û�й������췽��
		 *   Ҫ����һ�� Pattern ����,��������ȵ����乫����̬����,������һ�� Pattern ����
		 *   �÷�������һ��������ʽ��Ϊ���ĵ�һ������
		 *  
		 *  Matcher ��
		 *    Matcher �����Ƕ������ַ������н��ͺ�ƥ�����������
		 *   �� Pattern ��һ��, Matcher û�й������췽��
		 *   ����Ҫ���� Pattern ����� matcher ���������һ�� Matcher ����
		 *   ��������
		 *      public int start()
		 *       ������ǰƥ��ĳ�ʼ����
		 *      public int start(int group)
		 *       ��������ǰ��ƥ������ڼ�,�ɸ�����������������еĳ�ʼ����
		 *      public int end()
		 *       �������ƥ���ַ�֮���ƫ����
		 *      public int end(int froup)
		 *       ��������ǰ��ƥ������ڼ�,�ɸ����������������е�����ַ�֮���ƫ����
		 *   ���ҷ���
		 *      public boolean lookingAt()
		 *       ���Խ�������ͷ��ʼ�������������ģʽƥ��
		 *      public boolean find()
		 *       ���Բ�����ģʽƥ����������е���һ��������
		 *      public boolean find(int start)
		 *       ���ô�ƥ����,Ȼ���Բ���ƥ���ģʽ,��ָ��������ʼ���������е���һ��������
		 *      public boolean matches()
		 *       ���Խ�����������ģʽƥ��
		 *   �滻����
		 *      public Matcher appendReplacement(StringBuffer sb,String replacement)
		 *       ʵ�ַ��ն���Ӻ��滻����
		 *      public StringBuffer appendTail(StringBuffer sb)
		 *       ʵ���ն���Ӻ��滻����
		 *      public String replaceAll(String replacement)
		 *       �滻ģʽ������滻�ַ�����ƥ����������е�ÿ��������
		 *      public String replaceFirst(String replacement)
		 *       �滻ģʽ������滻�ַ���ƥ����������еĵ�һ��������
		 *      public Static String quoteReplacement(String e)
		 *       ����ָ���ַ����������滻�ַ���
		 *   
		 *  PatternSyntaxException
		 *    PatternSyntaxException ��һ����ǿ���쳣��,����ʾһ��������ʽģʽ�е��﷨����
		 */
		String s="I am noob from runoob.com.";
		String p=".*runoob.*";
		boolean isMatch=Pattern.matches(p, s);
		System.out.println("s�Ƿ����'runoob'�ַ���:"+isMatch);
		
		//  start �� end ����
		String s1="\\bcat\\b";
		String s2="  cat caat  cat cattt cat";
		Pattern p1=Pattern.compile(s1);
		Matcher m1=p1.matcher(s2);
		int i1=0;
		while(m1.find()) {
			i1++;
			System.out.println("Match number:"+i1);
			System.out.println("start():"+m1.start());
			System.out.println("end():"+m1.end());
		}
		
		//  matches �� lookingAt ����
		String s3="foo",s4="fooooooooooooo",s5="ooooooofoooooooo";
		Pattern p2=Pattern.compile(s3);
		Matcher m2=p2.matcher(s4),m3=p2.matcher(s5);
		System.out.println("lookingAt():"+m2.lookingAt());
		System.out.println("matches():"+m2.matches());
		System.out.println("lookingAt():"+m3.lookingAt());

		/**
		 * ������
		 *   �������ǰѶ���ַ���һ��������Ԫ���д���ķ���,��ͨ���������ڵ��ַ����з���������
		 *   ��������ͨ���������Ҽ����俪���������
		 *   ��ͨ������ matcher ����� groupCount �������鿴���ʽ�ж��ٸ�����
		 *    groupCount ��������һ�� int ֵ,��ʾ matcher ����ǰ�ж��ٸ�������
		 *   ����һ���������(group(0)),�����Ǵ����������ʽ,���鲻������ groupCount �ķ���ֵ��
		 */
		String s6="THis order was placed for QT3000!OK?";
		String p3="(\\D*)(\\d+)(.*)";
		Pattern r2=Pattern.compile(p3);
		Matcher m4=r2.matcher(s6);
		if(m4.find()) {
			System.out.println("Found value:"+m4.group(0));
			System.out.println("Found value:"+m4.group(1));
			System.out.println("Found value:"+m4.group(2));
			System.out.println("Found value:"+m4.group(3));
		}else {
			System.out.println("NO MATCH");
		}
		
		/**
		 * ������ʽ�﷨
		 *   ������������, '\\'��ʾ:����Ҫ��������ʽ�в���һ����ͨ�ķ�б��,�벻Ҫ�����κ���������
		 *   �� Java ��,'\\'��ʾ:��Ҫ����һ��������ʽ�ķ�б��,���������ַ��������������
		 *   ����������������,һ��'\'�����Ծ���ת�������,���� Java ����Ҫ�������ܱ�����
		 *   \             ����һ���ַ����Ϊ�����ַ�
		 *   ^             ƥ�������ַ�����ʼ��λ��
		 *   $             ƥ�������ַ�����β��λ��
		 *   *             ��λ���ƥ��ǰ���ַ����ӱ��ʽ
		 *   +             һ�λ���ƥ��ǰ����ַ����ӱ��ʽ
		 *   ?             ��λ�һ��ƥ��ǰ����ַ����ӱ��ʽ
		 *   {n}            n �ǷǸ�����,����ƥ�� n ��
		 *   {n,}           n �ǷǸ�����,����ƥ�� n ��
		 *   {n,m}          n m �ǷǸ�����,����ƥ�� n ��,֮��ƥ�� m ��
		 *   ?             ��λ�һ��ƥ��ǰ����ַ����ӱ��ʽ
		 *   .             ƥ���"\r\n"֮����κε�һ�ַ�
		 *   (pattern)     ƥ�� pattern �������ƥ����ӱ��ʽ
		 *   (?:pattern)   ƥ�� pattern ���������ƥ����ӱ��ʽ
		 *   (?=pattern)   ƥ�� pattern ִ������Ԥ�������������ӱ��ʽ
		 *   (?!pattern)   ƥ�� pattern ִ�з���Ԥ�������������ӱ��ʽ
		 *   x|y           ƥ�� x �� y
		 *   [xyz]         ƥ���������һ�ַ�
		 *   [^xyz]        ƥ��δ�������κ��ַ�
		 *   [a-z]         ƥ��ָ����Χ�ڵ��κ��ַ�
		 *   [^a-z]        ƥ�䲻��ָ���ķ�Χ�ڵ��κ��ַ�
		 *   \b            ƥ���ַ��߽�
		 *   \B            ���ַ��߽�ƥ��
		 *   \cx           ƥ�� x ָʾ�Ŀ����ַ�
		 *   \d            �����ַ�ƥ��
		 *   \D            �������ַ�ƥ��
		 *   \f            ��ҳ��ƥ��
		 *   \n            ���з�ƥ��
		 *   \r            �س���ƥ��
		 *   \s            ƥ���κοհ��ַ�
		 *   \S            ƥ���κηǿհ��ַ�
		 *   \t            �Ʊ��ƥ��
		 *   \v            ��ֱ�Ʊ��ƥ��
		 *   \w            ƥ���κ������ַ�,�����»���,�� [A-Za-z0-9_] ��Ч
		 *   \W            ƥ���κηǵ����ַ�,�� [^A-Za-z0-9_] ��Ч
		 *   \xn           ƥ�� n(ʮ������ת����)
		 *   \num          ƥ�� num(������)
		 *   \n            ��ʶһ���˽���ת�����������
		 *   \nm           ��ʶһ���˽���ת�����������...
		 *   \nml          �� n �ǰ˽�����(0-3), m �� l �ǰ˽���(0-7)ʱ,ƥ��˽���ת���� nml
		 *   \+un          (ȥ��+�ᱨ��)ƥ�� n ,���� n ������λʮ����������ʾ�� Unicode �ַ� 
		 */
	}

}
