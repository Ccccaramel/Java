  package ding.com;

abstract class Animal1{  // 抽象类
	abstract void eat();  // 抽象方法
}
class Cat extends Animal1{
	@Override
	void eat() {
		// TODO Auto-generated method stub
		System.out.println("cat eat fish.");
	}
	void work() {
		System.out.println("catch mouse.");
	}
}
class Dog extends Animal1{
	@Override
	void eat() {
		// TODO Auto-generated method stub
		System.out.println("dog eat bone.");
	}
	void work() {
		System.out.println("stay behind.");
	}
	
}
public class JavaPolymorphism {
	
	public static void show(Animal1 a) {
		a.eat();
		if(a instanceof Cat) {
			Cat c =(Cat)a;
			c.work();
		}
		else if (a instanceof Dog) {
			Dog c=(Dog)a;
			c.work();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 多态的优点
		 *   1.消除类型之间的耦合关系
		 *   2.可替换性
		 *   3.可扩充性
		 *   4.接口性
		 *   5.灵活性
		 *   6.简化性
		 * 多态存在的三个必要条件
		 *   1.继承
		 *   2.重写
		 *   3.父类引用子对象
		 * 多态的实现方式
		 *   1.重写
		 *   2.接口
		 *   3.抽象类和抽象方法
		 */
		show(new Cat());
		show(new Dog());
		Animal1 a=new Cat();
		a.eat();
		Cat c=(Cat)a;
		c.work();
		
		/**
		 *  Java 中没有系函数的概念
		 * 它的普通函数就相当于 C++ 的虚函数,动态绑定是 Java 的默认行为
		 * 如果希望 Java 中不希望某个函数具有虚函数特性,可加上 final 关键字变成非虚函数
		 */
	}
}
