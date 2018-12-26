package com.yxj.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * 流操作包括三步  1.创建流  2.中间操作  3.终止操作
 */
public class CreateStreamTest {
    /**
     * 创建流
     */
    @Test
    public void test1(){
        //1.集合获取流
        List<String> list=new ArrayList<>();
        list.add("hello");
        list.add("world");
        //获得顺序流
        Stream<String> listStream = list.stream();
        //获得并行流
        //并行流就是把一个内容分成多个数据块，并用不同的线程分成多个数据块，并用不同的线程分别处理每个数据块的流。
        Stream<String> parallelStream = list.parallelStream();
        //Stream API 可以声明性地通过parallel() 与sequential() 在并行流与顺序流之间进行切换
        listStream.forEach(System.out::println);

        //2.数组获得流的方式
        String[] strArr=new String[]{"1","2","3"};
        Stream<String> arrStream = Arrays.stream(strArr);

        //3.使用Stream的静态方法获得流
        Stream<String> strArrStream = Stream.of(strArr);

        ////4.创建无限流
         //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream4.forEach(System.out::println);

        //生成
        Stream<Double> stream5 = Stream.generate(Math::random).limit(2);
        stream5.forEach(System.out::println);
    }
}
