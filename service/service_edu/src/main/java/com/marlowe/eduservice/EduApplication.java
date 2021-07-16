package com.marlowe.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-16 19:50
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.marlowe"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
