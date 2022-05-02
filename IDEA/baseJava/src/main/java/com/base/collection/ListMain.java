package com.base.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListMain {
    public static void main(String[] args) {
        List<String> arrayList= new ArrayList<>();
        arrayList.add("arrayList2");
        arrayList.add("arrayList3");
        arrayList.add("arrayList1");
        arrayList.forEach(System.out::println);

        List<String> linkedList = new LinkedList<>();
        linkedList.add("linkedList1");
        linkedList.add("linkedList2");
        linkedList.add("linkedList3");
        linkedList.forEach(System.out::println);
    }
}
