package com.yxj.stream;

import com.yxj.stream.domain.Person2;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream流的终止操作 收集
 * collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
 */
public class StreamStopOperate3 {
    List<Person2> emps = Arrays.asList(
            new Person2(102, "李四", 70, 6666.66, "BUSY"),
            new Person2(101, "张三", 18, 9999.99, "FREE"),
            new Person2(103, "王五", 28, 3333.33, "VOCATION"),
            new Person2(104, "赵六", 8, 7777.77, "BUSY"),
            new Person2(104, "赵六", 8, 7777.77, "FREE"),
            new Person2(104, "赵六", 8, 7777.77, "FREE"),
            new Person2(105, "田七", 38, 5555.55, "BUSY")
    );

    /**
     * Collectors的常用方法  toList  toSet toCollection
     */
    @Test
    public void test1(){
        //转list
        List<String> stringList = emps.stream().map(Person2::getName).collect(Collectors.toList());
        System.out.println(stringList);
        System.out.println("-----------------------------------------------");
        //统一的转集合 toCollection
        HashSet<String> collect = emps.stream().map(p -> {
            return p.getName();
        }).collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect);
        System.out.println("-----------------------------------------------");
        //统计集合长度  counting()  统计个数
        Long aLong = emps.stream().collect(Collectors.counting());
        System.out.println(aLong);
        System.out.println("-----------------------------------------------");
        //对流中的double属性进行求和 summingDouble
        Double doubleSum = emps.stream().collect(Collectors.summingDouble(Person2::getSalary));
        System.out.println(doubleSum);
        System.out.println("-----------------------------------------------");
        //查工资最大值 maxBy
        Optional<Double> doubleOptional = emps.stream().map(Person2::getSalary).collect(Collectors.maxBy(Double::compare));
        System.out.println(doubleOptional.get());
        System.out.println("-----------------------------------------------");
        //查工资最小的人 minBy
        Optional<Person2> minPerson = emps.stream().collect(Collectors.minBy((x, y) -> Double.compare(x.getSalary(), y.getSalary())));
        System.out.println(minPerson.get());
        //查工资平均值
        Double averageDouble = emps.stream().collect(Collectors.averagingDouble(Person2::getSalary));
        System.out.println(averageDouble);
        System.out.println("-----------------------------------------------");
        //查流中某个元素的统计值
        DoubleSummaryStatistics summaryStatistics = emps.stream().map(Person2::getSalary).collect(Collectors.summarizingDouble(Double::doubleValue));
        System.out.println(summaryStatistics.getMax());
        System.out.println(summaryStatistics.getAverage());
        System.out.println(summaryStatistics);
    }

    /**
     *  分组  Collectors的groupingBy
     */
    @Test
    public void test2(){
        //按实体类中某个属性分组  键为该属性值  值为 属性对应的实体对象集合
        Map<String, List<Person2>> collect = emps.stream().collect(Collectors.groupingBy(Person2::getDesc));
        System.out.println(collect);
        System.out.println("-----------------------------------------------");
        //多级分组,先按Desc分组 然后按年龄分组
        Map<String, Map<String, List<Person2>>> collectMap = emps.stream().collect(Collectors.groupingBy(Person2::getDesc, Collectors.groupingBy(p -> {
            if (p.getAge() >= 60) {
                return "老年";
            } else if (p.getAge() <= 18) {
                return "年轻人";
            } else {
                return "中年";
            }
        })));
        System.out.println(collectMap);
    }

    /**
     * 收集的其他常用方法
     * partitioningBy(分区)
     * 连接 join  将字符串的集合中每个字符串用指定的连接符连接起来
     * reducing  归约
     */
    @Test
    public void test3(){
        //按照true和false分组
        //如下面年龄大于30的分到键为true的那一组
        Map<Boolean, List<Person2>> booleanCollect = emps.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 30));
        System.out.println(booleanCollect);
        //链接 join
        String s = emps.stream().map(Person2::getName).collect(Collectors.joining(",", "first", "last"));
        System.out.println(s);
        //归约 reducing
        Optional<Double> collect = emps.stream().map(Person2::getSalary).collect(Collectors.reducing(Double::sum));
        System.out.println(collect.get());
    }
}
