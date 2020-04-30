package com.yxj.spring.controller;

import com.yxj.spring.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;

@Controller
public class AnimalController {

    @Autowired(required = false)
//    @Qualifier("animalRe")
    private Animal animalRe;


    public Animal getAnimalRe() {
        return animalRe;
    }

    public void setAnimalRe(Animal animalRe) {
        this.animalRe = animalRe;
    }
}
