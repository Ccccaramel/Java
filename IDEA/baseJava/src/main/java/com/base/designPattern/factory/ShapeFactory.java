package com.base.designPattern.factory;

public class ShapeFactory {
    public Shape getShape(String type){
        if("CIRCLE".equals(type)){
            return new Circle();
        }
        else if("RECTANGLE".equals(type)){
            return new Rectangle();
        }
        else if("SQUARE".equals(type)){
            return new Square();
        }
        else{
            return null;
        }
    }
}
