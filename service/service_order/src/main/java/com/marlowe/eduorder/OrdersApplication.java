package com.marlowe.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: onlineEducation
 * @description:
 * @author: Marlowe
 * @create: 2021-07-28 18:16
 **/
@EnableFeignClients
@EnableDiscoveryClient // Nacos注册
@ComponentScan(basePackages = {"com.marlowe"})
@SpringBootApplication
@MapperScan("com.marlowe.eduorder.mapper")
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class,args);
    }
}
