package com.marlowe.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-24 10:31
 **/

@SpringBootApplication
@ComponentScan(basePackages = {"com.marlowe"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}