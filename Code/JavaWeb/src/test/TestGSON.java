package test;

import com.google.gson.Gson;

public class TestGSON {
	public static class Person{
		private String name;
		private int age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		Person person=new Person();
		person.setName("Tom");
		person.setAge(12);
		String js=gson.toJson(person);
		System.out.println("js:"+js);
		
	}

}
