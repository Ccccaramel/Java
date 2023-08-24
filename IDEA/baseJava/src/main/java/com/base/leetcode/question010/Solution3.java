package com.base.leetcode.question010;

// 16
public class Solution3 {
    public boolean isMatch(String s, String p) {
        if(s==p||p==".*"){
            return true;
        }
        return recurrence(s, p,s.length()-1, p.length()-1);
    }

    public boolean recurrence(String s, String p,int si,int pi){
        if(si<0){  // s 已经遍历完毕
            if ((pi+1)%2!=0) {  // 检查 p 前部分是否可为空,只有 ?*?*?*?... 符合
                return false;
            }
            for(;pi>=0;pi-=2){
                if(p.charAt(pi)!='*'){  // 当前字符为 字母 或 .
                    return false;
                }
            }
            return true;
        }
        else if(pi<0){  // s 未遍历完毕
            return false;
        }

        // 至此, p 和 s 后部分均不为空
        if(s.charAt(si)==p.charAt(pi)||p.charAt(pi)=='.'){
            return recurrence(s,p,si-1,pi-1);  // C
        }
        else if(p.charAt(pi) == '*'){  // p 的前一个是 *
            if(s.charAt(si)==p.charAt(pi-1)||p.charAt(pi-1)=='.'){  // C* 或 .*
                return recurrence(s,p,si-1,pi)||recurrence(s,p,si-1,pi-2)||recurrence(s,p,si,pi-2);
            }
            else {
                return recurrence(s,p,si,pi-2);  // 不匹配
            }
        }

        return false;
    }
}