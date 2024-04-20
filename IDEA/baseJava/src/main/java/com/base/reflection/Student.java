package com.base.reflection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Student {
    public static final String FLAG = "123";
    private Integer age;
    private String name;

    public Student() {
        System.out.println("*0*");
    }

    private Student(String name) {
        this.age = 10;
        this.name = name;
        System.out.println("*1*");
    }

    public Student(Integer age, String name) {
        this.age = age;
        this.name = name;
        System.out.println("*2*");
    }

    public void say(){
        System.out.println("hi!");
    }

    private String myName(String info){
        return this.name+info;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException, FileNotFoundException {
        Student student= new Student(1,"Tom");

        // 获取 Class 对象的三种方式
        Class c1 = Student.class;
        Class c2=Class.forName("com.base.reflection.Student");
        Class c3=student.getClass();

        System.out.println("----- 获取类名 -----");

        System.out.println("全类名:" + c1.getName());
        System.out.println("类名:" + c1.getSimpleName());
        System.out.println("类的包名:" + c1.getPackageName());

        System.out.println("----- 获取所有构造方法的名称以及参数个数 -----");
        Constructor[] constructors = c1.getDeclaredConstructors();  // 推荐使用
//        constructors = c1.getConstructors();  // 只能拿 public 修饰的构造器
        for (Constructor constructor : constructors) {
            System.out.println("构造器:"+constructor.getName()+",参数个数:"+constructor.getParameterCount());
        }

        System.out.println("----- 根据参数类型获取所有构造方法的名称以及参数个数 -----");
        Constructor constructor = c1.getDeclaredConstructor(String.class);  // 推荐使用
//        constructor = c1.getConstructor(Integer.class,String.class);  // 只能拿 public 修饰的构造器
        System.out.println("构造器:"+constructor.getName()+",参数个数:"+constructor.getParameterCount());


        System.out.println("----- 获取构造器并实例化 -----");

        constructor.setAccessible(true);  // 表示禁止检查访问控制级别,即解除限制仅 public 修饰的构造器可反射(暴力反射),不过似乎不设置也行,但破坏了封装性
        Student s=(Student)constructor.newInstance("Mary");  // 实例化,本质调用构造方法
        System.out.println("s:"+s.toString());

        System.out.println("----- 获取所有成员变量 -----");
        for (Field declaredField : c1.getDeclaredFields()) {  // 推荐
//        for (Field declaredField : c1.getFields()) {  // 获取 public 成员变量
            System.out.println(declaredField.getName() + " > " + declaredField.getType());
        }

        System.out.println("----- 根据参数类型获取成员变量 -----");
        Field declaredField = c1.getDeclaredField("name");  // 推荐
//        declaredField = c1.getField("FLAG");  // 根据参数类型获取 public 成员变量
        System.out.println(declaredField.getName() + " > " + declaredField.getType());

        System.out.println("----- 成员变量赋值 -----");
        declaredField.set(student,"Jerry");
//        declaredField.setAccessible(true);  // 暴力反射,禁止检查访问控制,不过不需要
        System.out.println("赋值:"+student);

        System.out.println("----- 成员变量取值 -----");
        System.out.println("取值:"+(String)declaredField.get(student));

        System.out.println("----- 获取所有成员方法 -----");
//        c1.getMethods();  // 只能获取 public 修饰的方法
        for (Method declaredMethod : c1.getDeclaredMethods()) {
            System.out.println(declaredMethod.getName()+" > "+declaredMethod.getParameterCount()+" > "+declaredMethod.getReturnType());
        }

        System.out.println("----- 获取单个成员方法 -----");
//        Method method = c1.getMethod("myName", String.class);  // 只能获取 public 修饰的方法
        Method method = c1.getDeclaredMethod("myName", String.class);
        System.out.println(method.getName()+" > "+method.getParameterCount()+" > "+method.getReturnType());

        System.out.println("----- 执行成员方法 -----");
//        method.setAccessible(true); // 暴力反射,禁止检查访问控制,不过不需要
        System.out.println("执行:"+method.invoke(student,"!!!"));

        System.out.println("----- 数据持久化 -----");
        Student.save(student);
    }

    public static void save(Object obj) throws IllegalAccessException, FileNotFoundException {
        Class c=obj.getClass();
        PrintStream ps = new PrintStream(new FileOutputStream("baseJava/src/main/java/com/base/reflection/data.txt",true));
        ps.println("------"+c.getSimpleName()+"------");
        for (Field declaredField : c.getDeclaredFields()) {
            String name = declaredField.getName();
            String age = declaredField.get(obj)+"";
            ps.println(name+":"+age);
        }
        ps.close();
    }
}
