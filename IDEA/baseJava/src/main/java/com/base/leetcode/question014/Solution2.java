package com.base.leetcode.question014;

public class Solution2 {
    public String longestCommonPrefix(String[] strs) {
        for(int i=0;i<strs.length;i++){
            if(strs[i].length()==0){
                return "";
            }
        }
        if(strs.length==1){
            return strs[0];
        }
        int end=-1;
        boolean loop=true;
        while(loop){
            for(int i=1;i<strs.length;i++){
                if(strs[i].length()>(end+2)&&strs[i-1].length()>(end+2)&&strs[i].charAt(end+1)!=strs[i-1].charAt(end+1)){
                    loop=false;
                }
            }
            end++;
        }
        return strs[0].substring(0,end);
    }
}
