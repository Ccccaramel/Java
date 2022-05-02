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
        System.out.println(a.pubA);
        a.show();
        System.out.println("---同包继承B(A)---");
        ClassB b=new ClassB();
        System.out.println(b.pubA);
        System.out.println(b.pubB);
        b.show();
        System.out.println("---同包属性C(B)---");
        ClassC c=new ClassC();
        System.out.println(c.pubC);
        System.out.println(c.getB().pubA);
        System.out.println(c.getB().pubB);
        c.show();
        System.out.println("---异包继承D(B)---");
        ClassD d=new ClassD();
        System.out.println(d.pubA);
        System.out.println(d.pubB);
        d.show();
        System.out.println("---异包属性E(B)---");
        ClassE e=new ClassE();
        System.out.println(e.getB().pubA);
        System.out.println(e.getB().pubB);
        e.show();
    }
}
