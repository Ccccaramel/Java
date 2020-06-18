package ding.com;import java.io.ObjectInputStream.GetField;

public class JavaString {

	public static void main(java.lang.String[] args) {
		// TODO Auto-generated method stub
		String s1="Hello world!";
		char[] c1= {'h','i','!'};
		System.out.println(s1);
		System.out.println(c1);
		String s2=new String(c1);
		System.out.println(s2+" s2³¤¶È:"+s2.length());
		s1=s1.concat(s2);
		System.out.println(s1);
		System.out.printf("%f %d %s",3.1,2,"qwe");
	}

}
