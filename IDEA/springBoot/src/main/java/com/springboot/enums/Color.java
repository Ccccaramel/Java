package com.springboot.enums;

public enum Color {
    RED("#ff0000"),GREEN("#00ff00"),BLUE("#0000ff");

    private final String code;

    Color(String code) {
        this.code=code;
        System.out.println("toString(): "+this.toString());
    }

    @Override
    public String toString() {
        return "Color{" +
                "code='" + code + '\'' +
                "}; " +
                "name: "+super.name();
    }
}