package com.base.tree;

import java.util.ArrayList;
import java.util.List;

import static com.base.tree.Tree.transformation;

public class Main {
    /**
     * 通过继承,调用统一的接口实现tree转换
     * @param args
     */
    public static void main(String[] args) {
        List<Address> addressList=new ArrayList<>();
        addressList.add(new Address(0,-1,"root"));
        addressList.add(new Address(1,0,"1"));
        addressList.add(new Address(2,0,"2"));
        addressList.add(new Address(3,0,"3"));
        addressList.add(new Address(4,1,"1-1"));
        addressList.add(new Address(5,1,"1-2"));
        addressList.add(new Address(6,5,"2-1"));
        addressList.add(new Address(7,3,"1-3"));
        addressList.add(new Address(8,3,"1-4"));
        addressList.add(new Address(9,6,"3-1"));
        addressList.add(new Address(10,9,"4-1"));
        List<Address> addressesTree = transformation(addressList);
        System.out.println("tree:"+addressesTree);

    }
}
