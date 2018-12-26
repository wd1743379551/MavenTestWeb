package com.yxj.stream;

import com.yxj.stream.domain.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试流的中间操作  中间操作包括
 * 1.筛选切片 2.映射转换 3.排序
 * 多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理，而在终止操作时一次性全部处理，称为“惰性求值”。
 * 注意 一个流发生终止操作后 后面不能再继续使用其做中间操作
 */
public class StreamOperateTest {
    List<Person> emps = Arrays.asList(
            new Person(102, "李四", 59, 6666.66),
            new Person(101, "张三", 18, 9999.99),
            new Person(103, "王五", 28, 3333.33),
            new Person(104, "赵六", 8, 7776.77),
            new Person(104, "赵六", 8, 7777.77),
            new Person(104, "赵六", 8, 7777.77),
            new Person(105, "田七", 38, 5555.55)
    );
    /**
     筛选与切片的中间操作有以下4种
     filter——接收 Lambda ， 从流中排除某些元素。
     limit——截断流，使其元素不超过给定数量。
     skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */

    /**
     * 测试filter  筛选出满足条件的
     */
    @Test
    public void test1() {
        //通过流的方式来找出年龄大于20的
        Stream<Person> stream2 = emps.stream();
        Stream<Person> filterStream = stream2.filter(x -> {
            System.out.println("测试过滤操作");
            return x.getAge() > 20;
        });
        //这个是内部迭代
        filterStream.forEach(System.out::println);
        //执行上面这句话才输出"测试过滤操作"这句,说明前面并没有执行，只是在设置条件
    }

    /**
     * 使用传统方式迭代遍历
     */
    @Test
    public void test2() {
        Iterator<Person> iterator = emps.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            System.out.println(person);
        }
    }

    /**
     * 测试limit方法  截取流中前N个元素
     */
    @Test
    public void test3() {
        Stream<Person> limitStream = emps.stream().filter(x -> {
            System.out.println("测试截取方法");
            return x.getAge() > 20;
        }).limit(2);
        //上面的多个中间操作生成一个流水线  执行下面的终止操作时一次性输出
        limitStream.forEach(System.out::println);
    }

    /**
     * 测试distinct方法
     * 去除重复
     */
    @Test
    public void test4() {
        emps.parallelStream().distinct().forEach(x -> {
            System.out.println(x);
        });
    }


    /**
     * 测试skip方法  (跳过集合的前n个元素，若n大于集合长度,返回空流)
     */
    @Test
    public void test5() {
        emps.parallelStream().filter(x -> {
            System.out.println("测试skip操作");
            return x.getSalary() > 5000;
        }).skip(4).forEach(System.out::println);
    }

    /**
     * 中间操作2  转换集合元素(映射)  有以下几种
     * map(Function f)	接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * mapToDouble(ToDoubleFunction f)	接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream
     * mapToInt(ToIntFunction f)	接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的IntStream
     * mapToLong(ToLongfunction f)	接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的LongStream
     * flatMap(Function f)	接收一个函数作为参数,当函数传的的返回值就是一个stream时使用,将流中的每个值都换成另一个流，然后把所有流连接成一个流
     *  接收的函数有两种写法
     *  一种是lambda表达式 如 x->{ }
     *  还有一种是 类名::方法名 在此种情况下 流中的每个元素可以作为调用者或者参数
     */

    /**
     * 测试map方法 接收 Lambda ，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test6() {
        //lambda表达式后面大括号时 需要有return来返回值  当只有一行代码没有大括号时,直接用该代码的返回值做为返回值
        Stream<String> stringStream = emps.parallelStream().map(x -> {
           return  x.getName();
        });
        //map方法接收一个函数 函数应用在集合得到的stream的每个元素上 形成一个新的流 连接返回值
        stringStream.forEach(System.out::println);

        String [] strArr=new String[]{"aaa","ccc","ddd","Eee","ZZZ"};
        Stream<String> arrStream = Arrays.stream(strArr);
        //这里就是没有大括号的时候
        Stream<String> stringStream2 =arrStream.map(x ->
             x.toUpperCase()
        );
        stringStream2.forEach(System.out::println);

        //另外一种形式的函数传给map方法
        Arrays.stream(strArr).map(String::toLowerCase).forEach(x->{
                    System.out.println(x);
        });

        //不使用flatMap和使用flatMap的区别
        //1.当不使用flatMap时Stream的泛型就是一个Stream
        //此时下面的 rStream 流中每个元素是一个Stream
        Stream<Stream<Character>> rStream = emps.stream().map(Person::getName).map(StreamOperateTest::filterCharacter);
        //遍历就要双层遍历
        rStream.forEach(x->{
            x.forEach(System.out::println);
        });
        //2.当使用flatMap来接收时()
        //此时会将函数返回的流拼成一个流
        Stream<Character> characterStream = emps.stream().map(x -> {
            return x.getName();
        }).flatMap(y ->
                filterCharacter(y)
        );
        characterStream.forEach(z->{
            System.out.println(z);
        });
    }

    //将一个字符串生成一个字符组成的流
    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 流的中间操作3 排序
     *      sorted()	产生一个新流，其中按自然顺序排序
     *     sorted(Comparator comp)	产生一个新流，按指定的排序方式
     *
     */
    @Test
    public void test7(){
        //按元素自己的排序方式排序  需要元素实现comparable接口
        emps.stream().map(Person::getSalary).sorted().forEach(System.out::println);

        //给元素指定排序的接口 comparator是一个函数式接口
        emps.stream().sorted((x,y)->{
            if(x.getAge()!=y.getAge()){
                return x.getAge()-y.getAge();
            }
            if(x.getSalary()!=y.getSalary()){
                return Double.compare(x.getSalary(),y.getSalary());
            }
            return x.getPersonId()-y.getPersonId();
        }).forEach(System.out::println);
    }
}
