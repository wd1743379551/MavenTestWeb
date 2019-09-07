package com.yxj.generic;

public class Animal {

    protected int age;
    protected String type;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void eat() {
        System.out.println("animal eat");
    }
}
