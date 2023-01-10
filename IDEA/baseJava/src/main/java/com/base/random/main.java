package com.base.random;

public class main {
    public static void main(String[] args) {
        int m=0,n=10,i=4;
        StringBuilder code = new StringBuilder();
        while (i>0){
            int v = (int)Math.floor(Math.random()*(n-m));
            System.out.println(">>>"+v);
            code.append((char)(v + 48));
            i--;
        }
        System.out.println("code:"+code);
    }
}
