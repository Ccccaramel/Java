package com.base.innerClass;

import com.base.annotation.B;

public class InnerClassDemo {
    public static void main(String[] args) {
        Out out = new Out(1);
        out.say();

        System.out.println("--- 成员内部类 ---");
        Out.In in = out.new In(2);  // 方式一
        in.say();
        in = new Out(3).createIn(4);  // 方式二
        in.say();

        System.out.println("--- 静态内部类 ---");
        new Out.StaticIn(5).doing();
        Out.StaticIn.say();

        System.out.println("--- 匿名内部类创建对象 ---");
        InterFaceDemo interFaceDemo = new InterFaceDemo() {
            @Override
            public int add(int a, int b, int c) {
                return a + b + c;
            }
        };
        System.out.println("a+b=" + interFaceDemo.add(6, 7, 8));

        System.out.println("--- 通过已实现的内部类调用 ---");
        InterFaceDemo i = new InterFaceDemo.obj();
        System.out.println("obj.add():" + i.add(1, 3, 5));

        System.out.println("--- Lambda ---");
        InterFaceDemo interFaceDemo1 = (a, b, c) -> a + b + c;
        System.out.println("a+b=" + interFaceDemo1.add(8, 9, 10));

        InterFaceDemo interFaceDemo2 = (a, b, c) -> {
            System.out.println("a:" + a + ",b:" + b + ",c:" + c);
            return a + b + c;
        };
        System.out.println("a+b=" + interFaceDemo2.add(6, 7, 8));

        System.out.println("--- 匿名内部类创建对象的原始形态 ---");
        InterFaceDemo interFaceDemo3 = new InterFaceDemo() {
            @Override
            public int add(int a, int b, int c) {
                return a + b + c;
            }
        };
        System.out.println("a+b=" + interFaceDemo3.add(6, 7, 8));
    }
}

interface InterFaceDemo{
    int add(int a,int b,int c);
    class obj implements InterFaceDemo {
        @Override
        public int add(int a, int b, int c) {
            return a+b+c;
        }
    }
}

class Out{
    private int i;

    public Out(int i) {
        this.i = i;
    }

    public void say(){
        System.out.println("out_i:"+i);
    }

    public In createIn(int i){
        return new In(i);
    }

    class In{  // 成员内部类
        private int i;

        public In(int i) {
            this.i = i;
        }

        public void say(){
            System.out.println("Out.i:"+Out.this.i+",In.i:"+i);  // 外部类与内部类存在同名属性/方法时,若想在内部类访问需以 [外部类名].this.[属性/方法] 方式调用
        }
    }

    static class StaticIn{  // 静态内部类
        private static int i=100;
        private int j;

        public StaticIn(int j) {
            this.j=j;
        }
        public void doing(){
            System.out.println("StaticIn.j:"+j);
        }
        public static void say() {
            System.out.println("StaticIn.i(静态属性):"+i);
        }
    }
}