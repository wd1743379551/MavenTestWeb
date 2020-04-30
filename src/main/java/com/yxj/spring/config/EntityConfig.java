package com.yxj.spring.config;

import com.yxj.spring.controller.AnimalController;
import com.yxj.spring.entity.Animal;
import com.yxj.spring.entity.AnimalDefineRegistor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Configuration
//@Import({AnimalDefineRegistor.class})
public class EntityConfig {

    @Bean
    public AnimalController animalController() {
        return new AnimalController();
    }
    @Bean
    public Animal animal() {
        return new Animal();
    }


}
