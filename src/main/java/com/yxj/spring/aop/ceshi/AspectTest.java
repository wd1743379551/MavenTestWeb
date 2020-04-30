package com.yxj.spring.aop.ceshi;

import com.yxj.spring.aop.config.GlobalConfig;
import com.yxj.spring.aop.target.BusinessService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(GlobalConfig.class);
        BusinessService service = applicationContext.getBean(BusinessService.class);
//        service.selectValue();
        int value = service.addValue(5, 2);
        System.out.println("测试方法返回:" + value);
    }
}
