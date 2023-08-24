package com.base.leetcode.question010;

// 109
public class Solution2 {

    public boolean isMatch(String s, String p) {
        if(s==p||p==".*"){
            return true;
        }

        return recurrence(s, p,0, 0);
    }

    public boolean recurrence(String s, String p,int si,int pi){

        if(si==s.length()){  // s 已经遍历完毕,检查 p 后续是否可为空
            for(;pi<p.length();pi++){
                if(p.charAt(pi)!='*'&&(pi+1==p.length()||p.charAt(pi+1)!='*')){  // 当前字符为 字母 或 .
                    return false;
                }
            }
            return true;
        }
        else{  // s 未遍历完毕
            if(pi==p.length()){
                return false;
            }
        }

        // 至此, p 和 s 后部分均不为空
        if(s.charAt(si)==p.charAt(pi)||p.charAt(pi)=='.'){
            if(recurrence(s,p,si+1,pi+1)){  // C
                return true;
            }
            else if(pi + 1 < p.length() && p.charAt(pi + 1) == '*'){  // p 的下一个是 *
                if(recurrence(s,p,si+1,pi)){  // C..
                    return true;
                }
                else if(recurrence(s,p,si+1,pi+1)){  // C
                    return true;
                }
                else if(recurrence(s,p,si,pi+2)){  // null
                    return true;
                }
            }
        }
        else{  // 字母不同!
            if(si+1<=s.length()&&pi+2<p.length()&&p.charAt(pi+1)=='*'&&recurrence(s,p,si,pi+2)){
                return true;
            }
            else{
                return false;
            }
        }

        return false;
    }
}