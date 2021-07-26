package com.marlowe.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-26 10:46
 **/
@SpringBootApplication
@ComponentScan({"com.marlowe"})
@MapperScan("com.marlowe.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
