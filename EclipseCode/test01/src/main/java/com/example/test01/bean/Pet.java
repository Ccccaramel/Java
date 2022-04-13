package com.example.test01.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
// 无参
@NoArgsConstructor
// 有参
@AllArgsConstructor
// get/set
@Data
// toString
@ToString
public class Pet {
    private String name;
    private Double weight;
}
