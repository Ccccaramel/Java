package com.base.designPattern.proxy;

public class Start {
    public static void main(String[] args) {
        Image image = new ProxyImage("a.png");
        image.display();
        System.out.println("...");
        image.display();
    }
}
