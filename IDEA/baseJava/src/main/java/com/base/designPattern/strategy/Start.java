package com.base.designPattern.strategy;

/**
 * 策略模式
 */
public class Start {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("1+1="+context.executeStrategy(1,1));

        context = new Context(new OperationSubtract());
        System.out.println("1-1="+context.executeStrategy(1,1));
    }
}
