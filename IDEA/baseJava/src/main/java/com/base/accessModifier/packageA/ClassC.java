package com.base.accessModifier.packageA;

/**
 * 属性
 */
public class ClassC{
    private String priC;
    protected  String proC;
    String defC;
    public String pubC;
    private ClassB b;

    public ClassC() {
        this.priC = "priC";
        this.proC = "proC";
        this.defC = "defC";
        this.pubC = "pubC";
        this.b=new ClassB();
    }

    public ClassB getB() {
        return b;
    }

    public void show(){
        System.out.println("***show(C)***"+b.defA+b.proA+b.pubA);
        System.out.println("***show(C)***"+b.pubB+b.proB+b.defB);
    }
}
