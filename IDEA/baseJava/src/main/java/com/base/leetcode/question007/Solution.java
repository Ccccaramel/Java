package com.base.leetcode.question007;

public class Solution {
    public int reverse(int x) {
        double res=0;
        for(int i=10;x!=0;){
            res=res*i+x%10;
            x=x/10;
        }
        if(res>0x7fffffff||res<0x80000000){
            return 0;
        }
        return (int)res;
    }
}
