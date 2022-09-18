package com.base.callbackFunctions;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Student{
    public void doHomeWork(String homeWork, String answer) {
        System.out.println("student.doHomeWork");
        System.out.println("作业：" + homeWork + " 答案：" + Objects.requireNonNullElse(answer, "(空白)"));

    }
    private static void goHome() {
        System.out.println("我先回家");
    }
    public static void main(String[] args) throws InterruptedException {
        Student student = new Student();
        String aHomeWork = "1+1=?";
        RoomMate roomMate = new RoomMate();
        String theAnswer = roomMate.getAnswer(aHomeWork);
        //1.这里并没有体现回调，只是别人将答案给你，你自己在抄上去
        student.doHomeWork(aHomeWork, theAnswer);

        //2.于是室友说，那你在调用getAnswer方法的时候，
        //除了传入作业，也把自己的引用放里面。这样我做完了，直接调用你的做作业方法就行了。这里其实已经体现了回调的意思。
        roomMate.getAnswer(aHomeWork,student);

        //3.基于对象的回调方式，不仅将回调的方法暴露了，也将其他不回调的方法暴露了，不安全，于是，基于接口回调的方式出现了
        //  因为是以接口作为参数类型的约定，在普通对象upcast向上转型之后将只暴露接口描述的那个方法，别人获取到这个引用，也只能使用这个（回调）方法。
        //  至此，遗留的重大安全隐患重要解决。
        WorkImpl workImpl= new WorkImpl();
        roomMate.getAnswer(aHomeWork, workImpl);

        //4.到目前为止，实现类Work接口的类，都可以传给roommate进行回调，于是乎，即使是匿名内部类(传递的仍然是引用)，只要实现了接口方法，依然可以调用
        roomMate.getAnswerMyWork(aHomeWork, new Work() {
            //这个方法随意发挥了，无需再写一个接口实现类去实现方法
            @Override
            public void doHomeWorkWithoutParam() {
                if (aHomeWork!=null){
                    System.out.println("answer is 2");
                }
            }
        });


        //5.但是回调方法的优势是异步回调，这样是其最被广为使用的原因。
        //  为了体现异步的意思，我们给好室友设置了个较难的问题，希望好室友能多好点时间思考。
        String hardHomework = "当x趋向于0，sin(x)/x =?";
        // 新开的线程纯粹用来等待好室友来写完作用。由于在好室友类中设置了3秒的等待时间，所以可以看到goHome方法将先执行。
        // 意味着该学生在告知好室友做作用后，就可以做自己的事情去了，不需要同步阻塞去等待结果。
        // 一旦好室友完成作业，写入作业本，该现场也就结束运行了。
        new Thread(() -> roomMate.askDifficult(hardHomework, new Work() {
            @Override
            public void doHomeWorkWithoutParam() {
                if ("1+1=?".equals(hardHomework)) {
                    System.out.println("作业：" + hardHomework + " 答案：" + 2);
                } else if ("当x趋向于0，sin(x)/x =?".equals(hardHomework)) {
                    System.out.print("思考：");
                    for (int i = 1; i <= 3; i++) {
                        System.out.print(i + "秒 ");
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println();
                    System.out.println("作业：" + hardHomework + " 答案：" + 1);
                } else {
                    System.out.println("作业：" + hardHomework + " 答案：" + "(空白)");
                }
            }
        })).start();
        goHome();
    }
}