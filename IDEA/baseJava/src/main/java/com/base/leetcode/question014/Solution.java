package com.base.leetcode.question014;

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        String s=strs[0];
        int minLen=strs[0].length();
        for(int i=1;i<strs.length;i++){  // 找到最短
            if(minLen>strs[i].length()){
                minLen=strs[i].length();
                s=strs[i];
            }
        }
        int end=s.length();  // 截取位置
        for(int i=0;i<strs.length;i++){
            if(s!=strs[i]){
                for(int j=0;j<end;j++){
                    if(s.charAt(j)!=strs[i].charAt(j)){
                        end=j;
                        break;
                    }
                }
            }
        }
        return s.substring(0,end);
    }
}
