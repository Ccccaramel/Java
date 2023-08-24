package com.base.accessModifier.packageB;

import com.base.accessModifier.packageA.ClassB;

/**
 * 继承
 */
public class ClassD extends ClassB{
    private String priD;
    protected String proD;
    String defD;
    public String pubD;

    public ClassD() {
        this.priD = "priD";
        this.proD = "proD";
        this.defD = "defD";
        this.pubD = "pubD";
    }

    public void show(){
        System.out.println("show(D):"+super.proA+","+super.pubA);
        System.out.println("show(D):"+super.proB+","+super.pubB);
    }
}
