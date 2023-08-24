package com.base.leetcode.question010;

// 19
public class Solution4 {
    public boolean isMatch(String s, String p) {
        if(s==p||p==".*"){
            return true;
        }

        boolean[][] res = new boolean[p.length()][s.length()];
        boolean  find = false;

        for(int i=0;i<p.length();i++) {
            if (i+1<p.length()&&p.charAt(i + 1) == '*') {
                if(p.charAt(i) == s.charAt(0)||p.charAt(i) == '.'){
                    if(!find){
                        res[i][0] = true;
                    }
                    res[i+1][0] = true;
                }
                else if(find||(i>1&&res[i-2][0])){
                    res[i+1][0] = true;

                }
                i++;
            }
            else if (p.charAt(i) == s.charAt(0)||p.charAt(i) == '.') {
                if(find){
                    break;
                }
                else{
                    res[i][0] = true;
                    find=true;
                }
            }
            else{
                break;
            }
        }

        boolean all;
        for(int m=1;m<p.length();m++){
            all=false;
            for (int n=1;n<s.length();n++){
                if(all){
                    res[m][n]=true;
                }
                else if(s.charAt(n)==p.charAt(m)||p.charAt(m)=='.'){
                    if(res[m-1][n-1]){
                        res[m][n]=true;
                    }
                }
                else if(p.charAt(m)=='*'){
                    if(p.charAt(m-1)=='.'){
                        if(m>1&&res[m-2][n]){
                            res[m][n]=true;
                        }
                        else if(res[m][n-1]){
                            res[m][n]=true;
                        }
                    }
                    if(s.charAt(n)==p.charAt(m-1)){
                        if(res[m][n-1]){
                            res[m][n]=true;
                        }
                        else if(res[m-1][n]){
                            res[m][n]=true;
                        }
                    }
                    if(m>1&&res[m-2][n]){
                        res[m][n]=true;
                    }
                }
            }
        }

        return res[p.length()-1][s.length()-1];
    }
}


//        System.out.print("   ");
//        for (int i=0;i<s.length();i++){
//            System.out.print(s.charAt(i)+" ");
//        }
//        System.out.println();
//        for(int a=0;a<p.length();a++){
//            System.out.print(p.charAt(a)+": ");
//            for(int b=0;b<s.length();b++){
//                System.out.print(res[a][b]?"T ":"F ");
//            }
//            System.out.println();
//        }
