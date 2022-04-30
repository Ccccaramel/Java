package com.base.accessModifier.packageB;

import com.base.accessModifier.packageA.ClassA;
import com.base.accessModifier.packageA.ClassB;

/**
 * 属性
 */
public class ClassE {
    private String priE;
    protected  String proE;
    String defE;
    public String pubE;
    private ClassB b;

    public ClassE() {
        this.priE = "priE";
        this.proE = "proE";
        this.defE = "defE";
        this.pubE = "pubE";
        this.b = new ClassB();
    }

    public ClassB getB() {
        return b;
    }

    public void show(){
        System.out.println("***show(E)***"+b.pubA);
        System.out.println("***show(E)***"+b.pubB);
    }
}
