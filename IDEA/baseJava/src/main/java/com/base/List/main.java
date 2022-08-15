package com.base.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Car{
    private int v1;

    public int getV1() {
        return v1;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public Car(int v1) {
        this.v1 = v1;
    }

    @Override
    public String toString() {
        return "Car{" +
                "v1=" + v1 +
                '}';
    }
}
public class main {
    public static void main(String[] args) {
        System.out.println(List.of(100).size());
        Map<String,Car> stringMap = new HashMap<>();
        stringMap.put("1",new Car(1));
        stringMap.get("1").setV1(2);
        System.out.println((stringMap.get("1").toString()));
    }
}
