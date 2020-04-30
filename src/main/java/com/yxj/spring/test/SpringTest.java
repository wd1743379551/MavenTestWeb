package com.yxj.spring.test;

import com.yxj.spring.bean.inject.SpringBeanConfig;
import com.yxj.spring.config.EntityConfig;
import com.yxj.spring.controller.AnimalController;
import com.yxj.spring.entity.Animal;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {


    @Test
    public void test1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(EntityConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        System.out.println(applicationContext.getBean("animalRe"));
        System.out.println(applicationContext.getBean("animal"));
        System.out.println(applicationContext.getBean(AnimalController.class).getAnimalRe());
        // 会报错不是唯一bean 指定primary可解决
//        System.out.println(applicationContext.getBean(Animal.class));
    }


    @Test
    public void test2() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        System.out.println(applicationContext.getBean("animalRe"));
        System.out.println(applicationContext.getBean("animal"));
        System.out.println(applicationContext.getBean(AnimalController.class).getAnimalRe());
        // 会报错不是唯一bean 指定primary可解决
//        System.out.println(applicationContext.getBean(Animal.class));
    }

}
