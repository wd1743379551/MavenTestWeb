package com.yxj.stream.domain;

import java.util.Objects;

public class Person2 {
    private int personId;
    private String name;
    private int age;
    private double salary;
    private String desc;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Person2(int personId, String name, int age, double salary, String desc) {
        this.personId = personId;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person2 person2 = (Person2) o;
        return personId == person2.personId &&
                age == person2.age &&
                Double.compare(person2.salary, salary) == 0 &&
                Objects.equals(name, person2.name) &&
                Objects.equals(desc, person2.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, name, age, salary, desc);
    }

    @Override
    public String toString() {
        return "Person2{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", desc='" + desc + '\'' +
                '}';
    }
}
