package com.base.stream;

import org.junit.jupiter.api.Test;

import java.util.List;

public class Demo {
    @Test
    public void fun1(){
        List<Integer> l1 = List.of(4, 2, 6, 0, 5, 1, 8, 3, 9, 7);
        l1.stream().filter(f->f%2==0).sorted().forEach(e->{
            if(e<5){
                System.out.println("e:"+e);
            }
            else{
                System.out.println("E:"+e);
            }
        });
    }

    @Test
    public void fun2(){
        List<Integer> l1 = List.of(4, 2, 6, 0, 5, 1, 8, 3, 9, 7);
        l1.forEach(System.out::println);
    }
}
