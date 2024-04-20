package com.base;
class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class classDemo {
    public static void main(String[] args) {
        Person p1 = new Person("Tom");
        Person p2 = p1;
        p2.setName("Jim");
        System.out.println(p1.getName());
    }
}
