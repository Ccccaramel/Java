package com.base.leetcode.question013;

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
        System.out.println("res:"+solution.romanToInt("III"));
//        System.out.println("res:"+solution.romanToInt("MCMXCIV"));
    }
}
