package com.yxj.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆变  ? super Person
 *
 * 传参传person的父类  添加时可以添加person子类对象  因为这个约定了 传进来的集合泛型一定是person及其父类
 *
 */
public class CovariantDemo3 {

    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        List<Woman> womanList = new ArrayList<>();

        CovariantDemo3.testCovariant(personList);
//        CovariantDemo3.testCovariant(womanList);// 编译报错
        CovariantDemo3.testCovariant(animalList);



    }

    // 泛型方法
    private static void testCovariant(List<? super Person> list) {


        list.add(new Person());
//        list.add(new Animal());// 编译报错
        list.add(new Woman());
        System.out.println(11);
    }
}
