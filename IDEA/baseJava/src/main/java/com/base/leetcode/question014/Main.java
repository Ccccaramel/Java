package com.base.leetcode.question014;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        running();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("timeElapsed:"+timeElapsed);

    }

    private static void running(){
        Solution2 solution = new Solution2();
//        System.out.println("res:"+solution.longestCommonPrefix(new String[]{"flower","flow","flight"}));
//        System.out.println("res:"+solution.longestCommonPrefix(new String[]{"dog","racecar","car"}));
//        System.out.println("res:"+solution.longestCommonPrefix(new String[]{"","racecar","car"}));
//        System.out.println("res:"+solution.longestCommonPrefix(new String[]{"a"}));
        System.out.println("res:"+solution.longestCommonPrefix(new String[]{"ab","a"}));
    }
}
