package com.base.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListMain {
    public static void main(String[] args) {
//        List<String> list1=new ArrayList<>();
//        System.out.println(list1.get(0));

        // ArrayList
        System.out.println("*** ArrayList ***");
        List<String> al1= new ArrayList<>();
        al1.add("arrayList1");
        al1.add("arrayList2");
        al1.add("arrayList3");
        List<String> al2=al1;  // 浅拷贝
        List<String> al3=new ArrayList<>(al1);  // 深拷贝
        System.out.println("al2:");
        al2.forEach(System.out::println);
        al1.add("arrayList4");
        System.out.println("al1:");
        al1.forEach(System.out::println);
        System.out.println("al2:");
        al2.forEach(System.out::println);
        System.out.println("al3:");
        al3.forEach(System.out::println);


        // LinkedList
        System.out.println("*** LinkedList ***");
        List<String> linkedList = new LinkedList<>();
        linkedList.add("linkedList1");
        linkedList.add("linkedList2");
        linkedList.add("linkedList3");
        linkedList.add("linkedList3");
        linkedList.forEach(System.out::println);

        System.out.println("*** List.of ***");
        // 定义一个 List
        System.out.println(List.of(100));


        // contains()
        System.out.println("*** contains() ***");
        if(Arrays.asList(1,2).contains(0)){
            System.out.println("T");
        }else{
            System.out.println("F");
        }

        // split()
        System.out.println("*** split() ***");
        String s2=",,,,1,2,3,4,,,,,5,5,,,,,";
        List<String> list2 = Arrays.asList(s2.split(","));
        System.out.println("list2:"+list2+",length:"+list2.size());

        String s3="";
        List<String> list3 = Arrays.asList(s3.split(","));
        System.out.println("list3:"+list3);

        System.out.println("*** contains() ***");
        String s4=",12,";
        System.out.println("exits:"+Arrays.asList("",",").contains(s4));
    }
}
