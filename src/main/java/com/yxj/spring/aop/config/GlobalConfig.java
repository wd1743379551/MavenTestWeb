package com.yxj.spring.aop.config;

import com.yxj.spring.aop.aspect.AspectDemo;
import com.yxj.spring.aop.target.BusinessService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class GlobalConfig {

    @Bean
    public BusinessService businessService() {
        return new BusinessService();
    }

    @Bean
    public AspectDemo aspectDemo() {
       return new AspectDemo();
    }


}
