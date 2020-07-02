package com.yxj.string;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String类的
 * 1.indexOf方法先找到第一个匹配的字符,然后依次比较,不满足直接跳subStr的长度继续比较
 * 2.replace和replaceAll的区别  replace替换字符串，replaceAll支持正则表达式的替换  这两个不改变原来的字符串 都输返回新的字符串对象
 * 3.String类的方法都是返回一个新的字符串对象 substring和trim方法都是。
 * 4.compareTo方法相等返回0 当参数是调用字符串的子串时，返回长度差。然后就是返回ASCII差值。
 * 5.toCharArray 返回一个新的字符数组,将原来字符串的字符复制到新的字符数组。
 * 6.StringBuffer和StringBuilder的substring方法和toString方法返回的都是一个新的字符串对象，其他方法是在原来的字符串数组上操作。
 * 7.StringBuffer和StringBuilder中方法返回值不是String时，就是在源字符数组上改变，返回值String就是新建了一个String对象。
 */
public class StringTest {


    @Test
    public void test1(){
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher("125");
        boolean b = matcher.matches();
        System.out.println(b);
        boolean b1 = Pattern.matches("\\d", "2");
        System.out.println(b1);
    }

    @Test
    public void test2() {
        StringBuffer stringBuffer = new StringBuffer("helloworld");
        System.out.println(stringBuffer.insert(2, "123"));
    }

    @Test
    public void test3() {
        String str = null;
        String str2 = "13";
        String str3 = str +str2;
        Object o = null;

        String str4 = o + str2;

        System.out.println(str4);

        System.out.println(str3);
//        System.out.println(str.toString());
    }


    @Test
    public void test4() {
        long l = System.nanoTime();
        System.out.println(l);
        long l1 = System.currentTimeMillis();
        System.out.println(l1);

        String s = RandomStringUtils.randomNumeric(16);
        System.out.println(s);

    }
}
