package ding.com;

public class JavaStringBuffer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer s1=new StringBuffer("Java Based:");
		s1.append("www");
		s1.append(".java.");
		s1.append("com");
		System.out.println(s1);
		System.out.println(s1.reverse());
		System.out.println(s1.delete(13, 19));  //[13,19)
		System.out.println(s1.capacity());
	}
}
