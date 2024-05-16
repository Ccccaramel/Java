package com.ding.office.utils;

/**
 * 数字相关
 */
public class MathUtils {

    /**
     * 生成指定位数的随机数
     * @return
     */
    public static int createNo(int lv) {
        double res = 0;
        double pow=Math.pow(10, lv);
        while(res<pow/10){
            res = Math.floor(Math.random() * pow);
        }
        return (int) res;
    }
}
