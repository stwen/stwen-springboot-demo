package com.example.demo.stream;

import lombok.Data;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2020/08/12
 **/
@Data
public class Person {
    private String name;
    private Integer age;
    private String country;
    private char sex;

    public Person(String name, Integer age, String country, char sex) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.sex = sex;
    }
}
