package com.base.accessModifier.packageA;

public class ClassA {
    private String priA;
    protected String proA;
    public String pubA;
    String defA;

    public ClassA() {
        this.priA = "priA";
        this.proA = "proA";
        this.pubA = "pubA";
        this.defA = "defA";
    }

    public String getPriA() {
        return priA;
    }

    public void setPriA(String priA) {
        this.priA = priA;
    }

    public String getProA() {
        return proA;
    }

    public void setProA(String proA) {
        this.proA = proA;
    }

    public String getPubA() {
        return pubA;
    }

    public void setPubA(String pubA) {
        this.pubA = pubA;
    }

    public String getDefA() {
        return defA;
    }

    public void setDefA(String defA) {
        this.defA = defA;
    }

    public void show(){
        System.out.println("show(A):"+this.priA+","+this.defA+","+this.proA+","+this.pubA);
    }
}
