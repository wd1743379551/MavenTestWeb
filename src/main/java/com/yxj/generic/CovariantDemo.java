package com.yxj.generic;

import sun.plugin.javascript.navig.LinkArray;

import java.util.ArrayList;
import java.util.List;

/**
 * java中的不变  就是指我们常见的泛型
 */
public class CovariantDemo{

    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        List<Woman> womanList = new ArrayList<>();
//        testCovariant(animalList);
//         CovariantDemo.<List<Person>>testCovariant(animalList);// 编译报错  泛型方法指定类型
         CovariantDemo.<List<Person>>testCovariant(personList);//

//        CovariantDemo.<Person>testCovariant(new Animal());// 编译报错
        CovariantDemo.<Person>testCovariant(new Woman());//

//        testCovariant(womanList);
    }

    // 泛型方法
    private static <T> void testCovariant(T  t) {
//        Animal p = (Animal) t;
//        p.eat();
        System.out.println(11);
    }
}
