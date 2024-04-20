package com.base.designPatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        Boss boss = new Boss(3);
        Action action = Helper.createProxy(boss);
        System.out.println(action.pay());
        System.out.println(action.work());
    }
}

interface Action{  // 行为
    String pay();
    String work();
}

class Boss implements Action{  // 头
    private Integer month;

    public Boss(Integer month) {
        this.month = month;
    }

    @Override
    public String pay() {
        System.out.println(this.month+"月的工资应该都到账了吧?");
        return "顺便说一下,下个月32号发工资!";
    }

    @Override
    public String work() {
        System.out.println(this.month+"月比较忙,回家没事干就加班!");
        return "最后走的记得关灯!";
    }
}

class Helper{  // 助手
    public static Action createProxy(Boss boss){
        Action action= (Action) Proxy.newProxyInstance(Helper.class.getClassLoader(),  // 指定一个类加载器
                new Class[]{Action.class},  // 指定生成的代理,即所包含的方法
                new InvocationHandler() {  // 指定生成的代理对象的功能实现
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  // 回调方法,(当前代理对象,代理对象所调用的方法,代理对象所调用的方法的参数)
                        if(method.getName().equals("pay")){
                            System.out.println("助手将员工工资信息提交给银行,并确认银行发放完毕!");
                        }
                        else if(method.getName().equals("work")){
                            System.out.println("助手将项目完成进度情况提交给Boss");
                        }
                        return method.invoke(boss,args);
                    }
                });
        return action;
    };
}