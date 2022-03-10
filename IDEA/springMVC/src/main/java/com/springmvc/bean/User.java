package com.springmvc.bean;

import java.util.Arrays;

public class User {
    private String name;
    private String password;
    private Integer id;
    private String sex;
    private Integer age;
    private String email;
    private String[] hobby;
    private String hobbys;

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }

    public User(String name, String password, Integer id, String sex, Integer age, String email, String[] hobby) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.hobby = hobby;
        setHobbys(Arrays.toString(this.hobby));
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", hobby=" + Arrays.toString(hobby) +
                ", hobbys='" + hobbys + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHobbys() {
        return hobbys;
    }

    public void setHobbys(String hobbys) {
        this.hobbys = hobbys;
    }
}
