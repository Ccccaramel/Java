package ding.com;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String address;
	public transient int SNN;
	public void mailCheck() {
		System.out.println("Mailing a check to "+name+" "+address);
	}
}

public class JavaSerialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 序列化
		 */
		
		System.out.println("---序列化对象---");
		Employee e=new Employee();
		e.name="Ali";
		e.address="Peer";
		e.SNN=123123;
		try {
			FileOutputStream fo=new FileOutputStream("../JavaBased/src/resourse/employee.ser");
			ObjectOutputStream out=new ObjectOutputStream(fo);
			out.writeObject(e);
			out.close();
			fo.close();
			System.out.println("saved");
		}catch(IOException i) {
			i.printStackTrace();
		}
		
		System.out.println("---反序列化对象---");
		Employee e1=null;
		try {
			FileInputStream fi=new FileInputStream("../JavaBased/src/resourse/employee.ser");
			ObjectInputStream in=new ObjectInputStream(fi);
			e=(Employee)in.readObject();
			in.close();
			fi.close();
		}catch(IOException i) {
			i.printStackTrace();
		}catch(ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
		System.out.println("Deserialized Employee..\n"+"Name:"+e.name+"\nAddress:"+e.address+"\nSSN:"+e.SNN);
		/*
		 *  readObject() 方法中的try/catch 代码块尝试捕获 ClassNotFoundException 异常
		 *  对于 JVM 可以反序列化对象,它必须是能够找到字节码的类
		 *  如果在反序列化对象的过程中找不该类,则抛出一个 ClassNotFoundException 异常
		 *   readObject() 方法的返回值被转化成 Employee 引用
		 *  当对象被序列化时,属性 SSN 的值为123123,但因为该属性是短暂的,该值没有被发送到输出流
		 *  所以反序列化后 Employee 对象的 SSN 的值为 0
		 */
	}

}
