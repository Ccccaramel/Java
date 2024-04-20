package com.base.leetcode.question013;
//    Symbol       Value
//    I             1
//    V             5
//    X             10
//    L             50
//    C             100
//    D             500
//    M             1000
public class Solution {
    public int romanToInt(String s) {
        int res=0;
        for(int i=s.length()-1;i>=0;i--){
            int v=0;
            if(s.charAt(i)=='I'){
                v=1;
            }
            else if(s.charAt(i)=='V'){
                v=5;
            }
            else if(s.charAt(i)=='X'){
                v=10;
            }
            else if(s.charAt(i)=='L'){
                v=50;
            }
            else if(s.charAt(i)=='C'){
                v=100;
            }
            else if(s.charAt(i)=='D'){
                v=500;
            }
            else{  // M
                v=1000;
            }
            if(v<res&&s.charAt(i)!=s.charAt(i+1)){
                v*=-1;
            }
            res+=v;
        }
        return res;
    }
}
