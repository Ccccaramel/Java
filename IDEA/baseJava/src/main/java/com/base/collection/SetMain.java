package com.base.collection;

import java.util.*;

public class SetMain implements Comparable<Object>{
    int no;
    String name;

    public SetMain(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        SetMain setMain=(SetMain) o;
        return Integer.compare(this.getNo(), setMain.getNo());
    }

    public static void main(String[] args) {
        HashSet<String> hashSet=new HashSet<>();
        hashSet.add("aaa");
        hashSet.add("bbb");
        hashSet.add("ccc");
        for (String s : hashSet) {
            System.out.println("hashSet: " + s);
        }
        hashSet.forEach(System.out::println);
        hashSet.forEach(str-> System.out.println("hashSet> "+str));

        TreeSet<String> treeSet=new TreeSet<>();
        treeSet.add("d");
        treeSet.add("h");
        treeSet.add("l");
        treeSet.add("e");
        treeSet.forEach(System.out::println);

        // 对对象进行排序
        TreeSet<SetMain> objTreeSet=new TreeSet<>();
        SetMain obj1 = new SetMain(3, "333");
        SetMain obj2=new SetMain(5,"555");
        SetMain obj3=new SetMain(1,"111");
        SetMain obj4=new SetMain(4,"444");
        objTreeSet.add(obj1);
        objTreeSet.add(obj2);
        objTreeSet.add(obj3);
        objTreeSet.add(obj4);
        objTreeSet.forEach(setMain -> {
            System.out.println("no:"+setMain.getNo()+",name:"+setMain.getName());
        });
        // 截取
        System.out.println("***subSet***");
        objTreeSet.subSet(obj1,obj2).forEach(setMain -> {
            System.out.println("no:"+setMain.getNo()+",name:"+setMain.getName());
        });
        System.out.println("***headSet***");
        objTreeSet.headSet(obj1).forEach(setMain -> {
            System.out.println("no:"+setMain.getNo()+",name:"+setMain.getName());
        });
        System.out.println("***tailSet***");
        objTreeSet.tailSet(obj1).forEach(setMain -> {
            System.out.println("no:"+setMain.getNo()+",name:"+setMain.getName());
        });
    }
}
