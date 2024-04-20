package com.base.lambda;

public class LambdaDemo {
    public static void main(String[] args) {
        Act a=new Act(){
            @Override
            public void say() {
                System.out.println("ActImpl say.");
            }
        };
        a.say();

        Act act=()-> System.out.println("ActImpl say by Lambda.");  // 相比匿名内部类,代码更少
        act.say();

        Skill skill=name->System.out.println("sing "+name);  // 只有一个参数则括号可省去
        skill.sing("123");

        Location location=(x,y)->{
            System.out.println("Location.move()");
            System.out.println("move to("+x+","+y+")");
        };
        location.move(2,4);
    }
}
interface Act{
    void say();
}
interface Skill{
    void sing(String name);
}
interface Location{
    void move(int x,int y);
}