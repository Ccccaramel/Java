package com.ding.hyld.utils;

public class EmailUtils {
     /**
     * 4位数字
     * @return
     */
    public static String getEmailCode() {
        int m=0,n=10,i=4;
        StringBuilder code = new StringBuilder();
        while (i>0){
            int v = (int)Math.floor(Math.random()*(n-m));
            code.append((char)(v + 48));
            i--;
        }
        return code.toString();
    }
}
