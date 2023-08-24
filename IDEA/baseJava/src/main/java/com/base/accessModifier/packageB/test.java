package com.base.accessModifier.packageB;

import com.base.accessModifier.packageA.ClassA;
import com.base.accessModifier.packageA.ClassB;
import com.base.accessModifier.packageA.ClassC;

/**
 * default:是否同包
 * protected:同包||子类
 *
 *            同类        同包(成员/子类) 不同包子类     不同包非子类(成员)
 * private    yes
 * default    yes        yes
 * protected  yes        yes           yes
 * public     yes        yes           yes          yes
 */
public class test {
    public static void main(String[] args){
        System.out.println("---基类A---");
        ClassA a=new ClassA();
        a.show();
        System.out.println("---同包继承B(A)---");
        ClassB b=new ClassB();
        b.show();
        System.out.println("---同包属性C(B)---");
        ClassC c=new ClassC();
        c.show();
        System.out.println("---异包继承D(B)---");
        ClassD d=new ClassD();
        d.show();
        System.out.println("---异包属性E(B)---");
        ClassE e=new ClassE();
        e.show();
    }
}
