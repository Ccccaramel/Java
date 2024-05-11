package com.base.designPattern.factory;

public class Start {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Circle circle= (Circle) shapeFactory.getShape("CIRCLE");
        circle.draw();
        Rectangle rectangle= (Rectangle) shapeFactory.getShape("RECTANGLE");
        rectangle.draw();
        Square square= (Square) shapeFactory.getShape("SQUARE");
        square.draw();
    }
}
