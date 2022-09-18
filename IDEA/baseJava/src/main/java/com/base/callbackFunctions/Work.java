package com.base.callbackFunctions;

public interface Work {
    default void doHomeWork(String question, String answer){}
    default void doHomeWorkWithoutParam(){};
}