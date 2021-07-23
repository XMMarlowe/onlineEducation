package com.marlowe.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-16 19:50
 **/
@EnableFeignClients
@EnableDiscoveryClient // Nacos注册
@SpringBootApplication
@ComponentScan(basePackages = {"com.marlowe"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
