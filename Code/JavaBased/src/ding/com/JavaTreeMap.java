package ding.com;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
class Person1 implements Comparable<Person1>{
	private int age;
	private String name;
	
	Person1(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	@Override
	public int compareTo(Person1 o) {
		// TODO Auto-generated method stub
		return o.age-this.age;
	}
	
	public String toString() {
		return name+":"+age;
	}
	
}
final class Person2{
	private int age;
	private String name;
	
	Person2(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	public int getAge() {
		return this.age;
	}

	public String toString() {
		return name+":"+age;
	}
}
public class JavaTreeMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("---◊‘∂®“Â≈≈–Ú---");
		Map<Integer,Integer> map=new TreeMap<>(); 
		map.put(1, null);
		map.put(10,1);
		map.put(3, 2);
		map.put(2, 3);
		for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		System.out.println("--- Comparable ≈≈–Ú(1)---");
		Map<Person1,Integer> m1=new TreeMap<>();
		Person1 p1=new Person1("Tom", 14);
		Person1 p2=new Person1("Jim", 11);
		Person1 p3=new Person1("Marry", 12);
		m1.put(p1, 1);
		m1.put(p2, 2);
		m1.put(p3, 3);
		for(Entry<Person1,Integer> entry:m1.entrySet()) {
			System.out.println(entry.getKey()+">"+entry.getValue());
		}
		
		System.out.println("--- Comparable ≈≈–Ú(2)---");
		Map<Person2,Integer> m2=new TreeMap<>(new Comparator<Person2>() {

			@Override
			public int compare(Person2 o1, Person2 o2) {
				// TODO Auto-generated method stub
				if(o1==null||o2==null) {
					return 0;
				}
				return o2.getAge()-o1.getAge();
			}
		});
		Person2 p4=new Person2("Tom", 14);
		Person2 p5=new Person2("Jim", 11);
		Person2 p6=new Person2("Marry", 12);
		m2.put(p4, 1);
		m2.put(p5, 2);
		m2.put(p6, 3);
		for(Entry<Person2,Integer> entry:m2.entrySet()) {
			System.out.println(entry.getKey()+">"+entry.getValue());
		}
	}
}
