package ding.com;
class A1{
	public int count(int a,int b) {
		return a+b;
	}
	public double count(int a,int b,int c) {  // жиди( Overload )
		return 0.1*(a+b+c);
	}
}
class B1 extends A1{
	public int count(int a,int b) {  // жиаД ( Override )
		return a+b;
	}
}
public class JavaOverrideAndOverload {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
