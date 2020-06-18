package ding.com;
class Animal{
	private String name;
	private int id;
	public Animal(String myName,int myid) {
		this.name=myName;
		this.id=myid;
	}
	public void eat() {
		System.out.println(name+" eatting!");
	}
	public void sleep() {
		System.out.println(name+" sleeping!");
	}
	public void introduction() {
		System.out.println("Hi!My id is"+this.id+" and my name is"+this.name+".");
	}
}
final class Penguin extends Animal{  // final 修饰的类不能被继承,即最终类
	public Penguin(String myName, int myid) {
		super(myName, myid);
		// TODO Auto-generated constructor stub
	}
	public void eat() {
		System.out.println("overwrite");
	}
	public void testEat() {
		this.eat();
		super.eat();
	}
}
public class JavaExtends{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Penguin p1=new Penguin("bill", 9);
		p1.testEat();
//		Penguin p2=new Animal("tom", 3); 
//		p2.eat();
		Animal a1=new Penguin("are", 4);
		a1.eat();
	}

}

