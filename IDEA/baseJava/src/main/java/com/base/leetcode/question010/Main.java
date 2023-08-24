package com.base.leetcode.question010;

// 正则表达式匹配
public class Main {
    public static void main(String[] args) {


        long start = System.currentTimeMillis();

        running();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("timeElapsed:"+timeElapsed);

    }

    private static void running(){
        Solution5 solution = new Solution5();

        System.out.println("------true------");
        System.out.println("res:"+solution.isMatch("ab", ".*b*"));
        System.out.println("res:"+solution.isMatch("bbbaccbbbaababbac", ".b*b*.*...*.*c*."));
        System.out.println("res:"+solution.isMatch("bbbaccbaa", ".b*b*d*.*.."));
        System.out.println("res:"+solution.isMatch("ab", ".*."));
        System.out.println("res:"+solution.isMatch("ab", ".*.."));
        System.out.println("res:"+solution.isMatch("a", "ab*"));
        System.out.println("res:"+solution.isMatch("aaa", "aaa"));
        System.out.println("res:"+solution.isMatch("aaa", "a.a"));
        System.out.println("res:"+solution.isMatch("ab", ".*"));
        System.out.println("res:"+solution.isMatch("bb", "b*b"));
        System.out.println("res:"+solution.isMatch("mississippi", "mis*is*ip*."));
        System.out.println("res:"+solution.isMatch("aa", "a*"));
        System.out.println("res:"+solution.isMatch("aaa", "ab*a*c*a"));
        System.out.println("res:"+solution.isMatch("aaaa", "a*a"));
        System.out.println("res:"+solution.isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*b"));
        System.out.println("res:"+solution.isMatch("aaaaabbccccdddd", "a*b*c*aaa*b*c*d*"));
        System.out.println("res:"+solution.isMatch("aaaaabbccccdddd", "a*b*c*ddb*c*d*"));
        System.out.println("res:"+solution.isMatch("aab", "c*a*b"));
        System.out.println("res:"+solution.isMatch("aaa", "ab*ac*a"));
        System.out.println("res:"+solution.isMatch("cbaacacaaccbaabcb", "c*b*b*.*ac*.*bc*a*"));
        System.out.println("res:"+solution.isMatch("baabbbaccbccacacc", "c*..b*a*a.*a..*c"));

        System.out.println("------false------");
        System.out.println("res:"+solution.isMatch("aa", "a"));
        System.out.println("res:"+solution.isMatch("aa", "aaaa"));
        System.out.println("res:"+solution.isMatch("aabbbbccccccdddeeeffff", "abbbbcddddezzzfff"));
        System.out.println("res:"+solution.isMatch("aa", "aaa"));
        System.out.println("res:"+solution.isMatch("a", "ab.*a"));
        System.out.println("res:"+solution.isMatch("aaaabbaaa", "ab.*a"));
        System.out.println("res:"+solution.isMatch("aaaaaa", "ab.*"));
        System.out.println("res:"+solution.isMatch("ab", ".*c"));
        System.out.println("res:"+solution.isMatch("mississippi", "mis*is*p*."));
        System.out.println("res:"+solution.isMatch("a", ".*..a*"));
        System.out.println("res:"+solution.isMatch("bccbbabcaccacbcacaa", ".*b.*c*.*.*.c*a*.c"));
        System.out.println("res:"+solution.isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*"));
        System.out.println("res:"+solution.isMatch("a", ".*.."));
        System.out.println("res:"+solution.isMatch("bb", "..*c"));
        System.out.println("res:"+solution.isMatch("aaa", ".a"));
        System.out.println("res:"+solution.isMatch("abb", "b*"));
    }
}
