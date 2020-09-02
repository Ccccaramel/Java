package ding.com;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class test1 {

    public static void main(String[] args){

        //集合一
        List<String> list1=new ArrayList<String>();
        list1.add("11");
        list1.add("12");
        list1.add("13");
        //集合二
        List<String> list2=new ArrayList<String>();
        list2.add("12");
        list2.add("15");
        list2.add("16");
        List<String> existList1=new ArrayList<String>(list1);
        List<String> existList2=new ArrayList<String>(list2);
        existList1.removeAll(list2);
        existList2.removeAll(list1);

        System.out.println("list1中不存在于list2中的："+existList1);
        System.out.println("list2中不存在于list1中的："+existList2);
    }

}
