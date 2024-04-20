package com.base.dataStructure;

import java.util.*;

public class DataStructureDemo {
    public static void main(String[] args) {
        System.out.println("*** Array ***");
        int[] a1 = new int[5];
        a1[0]=0;
        a1[1]=1;
        a1[2]=2;
        a1[3]=3;
        a1[4]=4;
        for (int i = 0; i < a1.length; i++) {
            System.out.println("a1["+i+"]:"+a1[i]);
        }
        // 无插入删除元素的方法,可通过遍历创建新的数组

        // 初始化方式一
//        int[][] a2={{0,1,2},{3,4,5},{6,7,8}};
        // 初始化方式二
        int[][] a2=new int[3][];
        a2[0] =new int[3];
        a2[1] =new int[3];
        a2[2] =new int[3];
        a2[0][0]= 0;
        a2[0][1]= 1;
        a2[0][2]= 2;
        a2[1][0]= 3;
        a2[1][1]= 4;
        a2[1][2]= 5;
        a2[2][0]= 6;
        a2[2][1]= 7;
        a2[2][2]= 8;
        for (int i = 0; i < a2.length; i++) {
            System.out.print("a2["+i+"]:");
            for (int j = 0; j < a2[i].length; j++) {
                System.out.print(a2[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("*** List ***");
        List<String> l1 = new ArrayList<>();
        l1.add("bbb");
        l1.add("ccc");
        l1.add(0,"aaa");
        l1.remove(2);
        for (int i = 0; i < l1.size(); i++) {
            System.out.println("l1["+i+"]:"+l1.get(i));
        }

        List<String> l2 = new LinkedList<>();
        l2.add("BBB");
        l2.add("CCC");
        l2.add(0,"AAA");
        for (int i = 0; i < l2.size(); i++) {

        }

    }
}
