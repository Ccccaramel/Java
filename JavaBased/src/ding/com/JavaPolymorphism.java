  package ding.com;

abstract class Animal1{  // ������
	abstract void eat();  // ���󷽷�
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
		 * ��̬���ŵ�
		 *   1.��������֮�����Ϲ�ϵ
		 *   2.���滻��
		 *   3.��������
		 *   4.�ӿ���
		 *   5.�����
		 *   6.����
		 * ��̬���ڵ�������Ҫ����
		 *   1.�̳�
		 *   2.��д
		 *   3.���������Ӷ���
		 * ��̬��ʵ�ַ�ʽ
		 *   1.��д
		 *   2.�ӿ�
		 *   3.������ͳ��󷽷�
		 */
		show(new Cat());
		show(new Dog());
		Animal1 a=new Cat();
		a.eat();
		Cat c=(Cat)a;
		c.work();
		
		/**
		 *  Java ��û��ϵ�����ĸ���
		 * ������ͨ�������൱�� C++ ���麯��,��̬���� Java ��Ĭ����Ϊ
		 * ���ϣ�� Java �в�ϣ��ĳ�����������麯������,�ɼ��� final �ؼ��ֱ�ɷ��麯��
		 */
	}
}
