package com.yxj.stream;

import com.yxj.stream.domain.Person2;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Stream的终止操作
 * allMatch(Predicate p)	检查是否匹配所有元素
 * anyMatch(Predicate p)	检查是否至少匹配一个元素
 * noneMatch(Predicate p)	检查是否没有匹配所有元素
 * findFirst()	返回第一个元素
 * findAny()	返回当前流中的任意元素
 * count()	返回流中元素总数
 * max(Comparator c)	返回流中的最大值
 * min(Comparator c)	返回流中的最小值
 * forEach(Consumer c)	内部迭代（使用Collection接口需要用户做迭代，称为迭代，相反，Stream API使用内部迭代，他帮你把迭代做了）
 */
public class StreamStopOperate {
    List<Person2> emps = Arrays.asList(
            new Person2(102, "李四", 59, 6666.66, "BUSY"),
            new Person2(101, "张三", 18, 9999.99, "FREE"),
            new Person2(103, "王五", 28, 3333.33, "VOCATION"),
            new Person2(104, "赵六", 8, 7777.77, "BUSY"),
            new Person2(104, "赵六", 8, 7777.77, "FREE"),
            new Person2(104, "赵六", 8, 7777.77, "FREE"),
            new Person2(105, "田七", 38, 5555.55, "BUSY")
    );

    /**
     *测试流中匹配
     */
    @Test
    public void test1(){
        //是否全匹配
        boolean b1 = emps.stream().allMatch(x -> {
            return x.getDesc().equals("BUSY");
        });
        System.out.println(b1);
        //是否匹配一个
        boolean b2 = emps.stream().anyMatch(x -> {
            return x.getDesc().equals("BUSY");
        });
        System.out.println(b2);
        //是否一个都不匹配
        boolean b3 = emps.stream().noneMatch(x -> x.getDesc().equals("s"));
        System.out.println(b3);
    }

    /**
     * 测试元素返回
     */
    @Test
    public void test2(){
        //返回第一个元素
        Optional<Person2> optional = emps.stream().
                sorted((p1, p2) -> Double.compare(p1.getSalary(), p2.getSalary()))
                .findFirst();
        System.out.println(optional.get());
        System.out.println("---------------------------------------");
        //返回任意一个元素
        Optional<Person2> any = emps.stream().filter(p->
           p.getDesc().equals("BUSY")
        ).findAny();
        System.out.println(any.get());
        System.out.println("---------------------------------------");
        //返回元素总数
        long count = emps.stream().filter(x->x.getAge()>20).count();
        System.out.println(count);
    }

    /**
     * 返回最大值 最小值
     */
    @Test
    public void test3(){
        //获取最大值(只获取工资)
        Optional<Double> max = emps.stream().map(Person2::getSalary).max(Double::compareTo);
        System.out.println(max.get());
        System.out.println("---------------------------------------");
        //获取最小值  (获取工资最低的人)
        Optional<Person2> min = emps.stream().min((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
        System.out.println(min.get());
    }
}
