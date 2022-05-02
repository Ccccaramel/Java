package com.base.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapMain {
    int no;
    String name;

    public MapMain(int no, String name) {
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

    public static void main(String[] args) {
        // HashMap
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("a","aaa");
        hashMap.put("b","bbb");
        hashMap.put("c","ccc");
        hashMap.forEach((s, s2) -> System.out.println(s+":"+s2));
        HashMap<String,String> hashMap1=new HashMap<>();
        hashMap.forEach((s, s2) -> {
            String put = hashMap1.put(s, s2 + "(clone1)");
            System.out.println("put1:"+put+" ");
        });
        hashMap1.forEach((s, s2) -> System.out.println(s+":"+s2));
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String s2 = entry.getValue();
            String put = hashMap1.put(key, s2 + "(clone2)");
            System.out.println("put2:" + put);
        }
        hashMap1.forEach((s, s2) -> System.out.println(s+":"+s2));

        Map<Integer,String> treeMap=new TreeMap<>();
        treeMap.put(3, "333");
        treeMap.put(5,"555");
        treeMap.put(1,"111");
        treeMap.put(4,"444");
        treeMap.forEach((integer, s) -> System.out.println("int:"+ integer +","+s));
    }
}
