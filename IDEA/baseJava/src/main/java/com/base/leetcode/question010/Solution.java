package com.base.leetcode.question010;

// 94
public class Solution {

    public boolean isMatch(String s, String p) {
        if(s==p||p==".*"){
            return true;
        }
        return recurrence(s, p,0, 0);
    }

    public boolean recurrence(String s, String p,int si,int pi){

        if(si==s.length()){  // s 已经遍历完毕,检查 p 后续是否可为空
            for(;pi<p.length();pi++){
                if(p.charAt(pi)!='*'){  // 当前字符为 字母 或 .
                    if(pi+1==p.length()){  // 是最后一个字符
                        return false;
                    }
                    else {
                        if(p.charAt(pi+1)!='*'){  // 下一个字符不为 *
                            return false;
                        }
                    }
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
        for(int i=si;si<s.length();si++){  // 遍历 s , i 用于 si 的存档
            if(s.charAt(i)==s.charAt(si)){  // 统计数量
                if(si+1!=s.length()){
                    continue;
                }
            }

            boolean warn = false;
            for(;pi<p.length();pi++){  // 遍历 p , j 用于 pi 的存档
                if(warn && (p.charAt(pi)!='*')){  // 上一个字符不匹配,且当前字符不为 * 则说明上一个字符数量至少为1,匹配失败
                    break;
                }
                if(s.charAt(i)==p.charAt(pi)){  // p当前字符与 s 的首个字符相同
                    if(recurrence(s,p,i+1,pi+1)){  // 各自下一个
                        return true;
                    }
                    else if(pi+2<=p.length()&&p.charAt(pi+1)=='*'){
                        if(recurrence(s,p,i,pi+2)){  // 将 ?* 看作 空
                            return true;
                        }
                        else if(recurrence(s,p,i+1,pi+2)){ // 将 ?* 看作一个字符
                            return true;
                        }
                        else{
                            break;
                        }
                    }
                    else{
                        warn=true;
                    }
                }
                else if(p.charAt(pi)=='.'){  // p 当前字符为 .
                    if(warn){
                        break;
                    }

                    if(pi+1<p.length()&& p.charAt(pi+1)=='*'){
                        if(recurrence(s,p,i,pi+2)){  // 空
                            return true;
                        }
                        else if(recurrence(s,p,i+1,pi)){  // 继续任意匹配
                            return true;
                        }
                        else if(recurrence(s,p,i+1,pi+2)){  // 终止任意匹配
                            return true;
                        }
                        else{
                            break;
                        }
                    }
                    else{
                        // 3.将 . 看作与s当前位置相同的字符,一对一匹配
                        if(recurrence(s,p,i+1,pi+1)){
                            return true;
                        }
                        else{
                            break;
                        }
                    }


                }
                else if(p.charAt(pi)=='*'){  // p当前字符为 *
                    if(s.charAt(i)!=p.charAt(pi-1)){  // p的前一个字符与s当前位置字符不相同
                        if(recurrence(s,p,i,pi+1)){  // s下一个字符
                            return true;
                        }
                        else{
                            break;
                        }
                    }
                    else if(s.charAt(i)==p.charAt(pi-1)||p.charAt(pi-1)=='.'){  // p的前一个字符与s当前位置字符相同 或 p 的前一个字符为 .
                        if(recurrence(s,p,i+1,pi)){  // s下一个字符
                            return true;
                        }
                        else if(recurrence(s,p,i+1,pi+1)){ // 停止当前字符匹配,匹配 p s 的下一个
                            return true;
                        }
                        else{
                            break;
                        }
                    }

                    if(warn){
                        warn=false;
                    }
                }
                else{  // 字母不同!
                    warn = true;
                }
            }

            // p遍历完毕而s没有
            if(si+1!=s.length()){
                break;
            }
        }

        return false;
    }
}
