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
        List<String> arrayList= new ArrayList<>();
        arrayList.add("arrayList2");
        arrayList.add("arrayList3");
        arrayList.add("arrayList1");
        arrayList.forEach(System.out::println);

        // LinkedList
        List<String> linkedList = new LinkedList<>();
        linkedList.add("linkedList1");
        linkedList.add("linkedList2");
        linkedList.add("linkedList3");
        linkedList.forEach(System.out::println);

        // 定义一个 List
        System.out.println(List.of(100).size());

        // contains()
        if(Arrays.asList(1,2).contains(0)){
            System.out.println("T");
        }else{
            System.out.println("F");
        }

        // split()
        String s2=",,,,1,2,3,4,,,,,5,5,,,,,";
        List<String> list2 = Arrays.asList(s2.split(","));
        System.out.println("list2:"+list2+",length:"+list2.size());

        String s3="";
        List<String> list3 = Arrays.asList(s3.split(","));
        System.out.println("list3:"+list3);

        String s4=",12,";
        System.out.println("exits:"+Arrays.asList("",",").contains(s4));
    }
}
