package com.base.random;

import org.junit.jupiter.api.Test;

/**
 * 模拟生成4位数字验证码
 */
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

    @Test
    public void fun(){
        System.out.println((int)(Math.random() * Math.pow(10, 14)));
        System.out.println((int)(Math.random() * Math.pow(10, 14)));

        System.out.println((int)Math.random() * Math.pow(10, 14));
        System.out.println((int)Math.random() * Math.pow(10, 14));
    }
}
