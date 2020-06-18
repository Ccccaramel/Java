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
		 * ���л�
		 */
		
		System.out.println("---���л�����---");
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
		
		System.out.println("---�����л�����---");
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
		 *  readObject() �����е�try/catch ����鳢�Բ��� ClassNotFoundException �쳣
		 *  ���� JVM ���Է����л�����,���������ܹ��ҵ��ֽ������
		 *  ����ڷ����л�����Ĺ������Ҳ�����,���׳�һ�� ClassNotFoundException �쳣
		 *   readObject() �����ķ���ֵ��ת���� Employee ����
		 *  ���������л�ʱ,���� SSN ��ֵΪ123123,����Ϊ�������Ƕ��ݵ�,��ֵû�б����͵������
		 *  ���Է����л��� Employee ����� SSN ��ֵΪ 0
		 */
	}

}
