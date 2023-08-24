package com.base.accessModifier.packageA;

/**
 * 继承
 */
public class ClassB extends ClassA{
    private String priB;
    protected  String proB;
    String defB;
    public String pubB;

    public ClassB() {
        this.priB = "priB";
        this.proB = "proB";
        this.defB = "defB";
        this.pubB = "pubB";
    }

    public String getPriB() {
        return priB;
    }

    public void setPriB(String priB) {
        this.priB = priB;
    }

    public String getProB() {
        return proB;
    }

    public void setProB(String proB) {
        this.proB = proB;
    }

    public String getDefB() {
        return defB;
    }

    public void setDefB(String defB) {
        this.defB = defB;
    }

    public String getPubB() {
        return pubB;
    }

    public void setPubB(String pubB) {
        this.pubB = pubB;
    }

    public void show(){
        System.out.println("show(B):"+super.pubA+","+super.defA+","+super.proA);
    }
}
