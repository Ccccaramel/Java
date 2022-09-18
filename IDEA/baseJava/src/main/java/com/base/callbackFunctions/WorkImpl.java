package com.base.callbackFunctions;

public class WorkImpl implements Work{
    @Override
    public void doHomeWork(String question,String answer) {
        if(question != null) {
            System.out.println("作业："+question+" 答案："+ 2);
        } else {
            System.out.println("作业："+question+" 答案："+ "(空白)");
        }
    }
}