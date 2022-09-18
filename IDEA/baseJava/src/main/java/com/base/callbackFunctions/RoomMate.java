package com.base.callbackFunctions;

public class RoomMate {
    public String getAnswer(String homework) {
        if("1+1=?".equals(homework)) {
            return "2";
        } else {
            return null;
        }
    }
    public void getAnswer(String homework, Student student) {
        if("1+1=?".equals(homework)) {
            student.doHomeWork(homework, "2");
        } else {
            student.doHomeWork(homework, "(空白)");
        }
    }
    public void getAnswer(String homework, Work work) {
        if("1+1=?".equals(homework)) {
            work.doHomeWork(homework,"2");
        } else {
            work.doHomeWork(homework,"(空白)");
        }
    }
    public void getAnswerMyWork(String homework, Work work) {
        System.out.println("question is "+homework);
        work.doHomeWorkWithoutParam();
    }
    public void askDifficult(String homework, Work work) {
        System.out.println("question is "+homework);
        work.doHomeWorkWithoutParam();
    }
}