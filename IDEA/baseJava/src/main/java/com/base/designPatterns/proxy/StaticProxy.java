package com.base.designPatterns.proxy;

public class StaticProxy {
    public static void main(String[] args) {
        Cook cook = new Cook(new Customer());
        cook.cooking();
    }
}

interface Skill{
    void cooking();
}

class Customer implements Skill{  // 客户
    @Override
    public void cooking() {
        System.out.println("吃吃吃!");
    }
}

class Cook implements Skill{  // 厨师-代理角色
    private Skill skill;

    public Cook(Skill skill) {
        this.skill = skill;
    }

    @Override
    public void cooking() {
        System.out.println("Cook 洗菜烹饪!");
        this.skill.cooking();
    }
}
