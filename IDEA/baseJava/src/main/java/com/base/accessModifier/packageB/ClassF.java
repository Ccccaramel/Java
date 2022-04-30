package com.base.accessModifier.packageB;

public class ClassF {
    public static void main(String[] args){
        int i=8,j=7;
        if(i-1>7){
            System.out.println("i:"+i);
        }
        else{
            System.out.println("j:"+j);
        }
        System.out.println("i>"+i);

        String s1=new String("123");
        String s2=new String("123");
        if(s1==s2){
            System.out.println("s1==s2");
        }
        if(s1.equals(s2)){
            System.out.println("s1.equals(s2)");
        }
    }
}
