package com.base.leetcode.question007;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        running();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("timeElapsed:"+timeElapsed);

    }

    private static void running(){
        Solution solution = new Solution();
        System.out.println("res:"+solution.reverse(-123));
        System.out.println("res:"+solution.reverse(-2147483648));
        System.out.println("res:"+solution.reverse(1534236469));
    }
}
