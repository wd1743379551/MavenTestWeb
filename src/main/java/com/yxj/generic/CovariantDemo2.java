package com.yxj.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 协变  ? extends Person
 *
 * 协变参数 集合中不能添加任意元素   因为代码不知道你传进来的集合具体泛型
 * 传参可以传指定类及其子类
 * 协变常用来做方法的返回值
 */
public class CovariantDemo2 {

    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        List<Woman> womanList = new ArrayList<>();

        CovariantDemo2.testCovariant(personList);//
        CovariantDemo2.testCovariant(womanList);//
//        CovariantDemo2.testCovariant(animolalList);// 编译报错



    }

    // 泛型方法
    private static void testCovariant(List<? extends Person> list) {

//        list.add(new Animal());  // 编译报错
//        list.add(new Person());// 编译报错
//        list.add(new Woman());// 编译报错

        System.out.println(11);
    }
}
