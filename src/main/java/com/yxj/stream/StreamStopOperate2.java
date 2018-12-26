package com.yxj.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 测试stream的终止操作  归约操作
 * reduce(T iden,BinaryOPerator b)	可以将流中元素反复结合起来，得到一个值。返回T
 * reduce(BinaryOperator b)	可以将流中元素反复结合起来，得到一个值，返回Optional< T>
 */
public class StreamStopOperate2 {

    List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8,9,10);

    @Test
    public void test1(){
        //reduce两个参数的  将集合中的元素值加起来  返回T也就是Integer
        Integer reduce = list.stream().filter(integer -> integer>5).reduce(100, (x, y) -> x + y);
        System.out.println(reduce);
    }

    @Test
    public void test2(){
        //reduce一个参数的  将流中的元素值加起来  返回Optional
        Optional<Integer> reduce = list.stream().reduce(Integer::sum);
        System.out.println(reduce.get());
    }


    //判断list中偶数的个数
    @Test
    public void test3(){
        Optional<Integer> reduce = list.stream().map(x -> {
            if (x % 2 == 0) {
                return 1;
            } else {
                return 0;
            }
        }).reduce(Integer::sum);
        System.out.println(reduce.get());
    }
}
