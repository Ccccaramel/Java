package com.base.regularExpression;

/**
 * 正则表达式
 */
public class Main {
    public static void main(String[] args) {
        String regex = "^1\\d{10}$";
        /**
         * 在正则表达式中 \d 表示一个数字
         * 但在 java 中存在一些特殊字符,拥有特定的功能,而且和普通的字符使用方式也不同
         * 其中 \ 是转义字符,它能将特殊字符转译成普通字符
         * 想要普通的 \ 字符用 \\ 代替,想要普通的 " 字符用 \" 代替...
         **/
        System.out.println(">"+"15971366760".matches(regex));

        String s = "\"\\\"";
        System.out.println("s:"+s);

        System.out.println("123abc.matches(\".*$\") > "+"123abc".matches(".*$"));
        System.out.println("123abc.matches(\"1.*c$\") > "+"123abc".matches("1.*c$"));
    }
}
