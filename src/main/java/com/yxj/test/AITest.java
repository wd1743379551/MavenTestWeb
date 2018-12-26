package com.yxj.test;

import org.junit.Test;

import java.util.Scanner;

public class AITest {



    public static void main(String [] args){
        Scanner scanner =new Scanner(System.in);
        while (true){
            String s = scanner.nextLine();
            if(s.equals("exit")){
                break;
            }
            String s1 = s.replaceAll("吗", "").replaceAll("[?]", "").replaceAll("嘛", "").replaceAll("你","我").replaceAll("去","来");
            System.out.println(s1);
        }
    }
}
