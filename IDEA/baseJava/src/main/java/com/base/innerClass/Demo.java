package com.base.innerClass;

public class Demo {
    public static void main(String[] args) {
        A a = new B(1);
        a.say(2);

        new B(){
            @Override
            public void say(int i) {
                System.out.println("i:"+i);
            }
        }.say(4);
    }
}

interface A{
    void say(int i);
}

class B implements A {
    private int amount;
    public B() {    }
    public B(int amount) {
        this.amount = amount;
    }
    @Override
    public void say(int i){
        System.out.println("amount:"+this.amount+",i:"+i);
    }
}