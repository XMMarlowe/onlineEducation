package com.marlowe.eduorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @program: onlineEducation
 * @description:
 * @author: Marlowe
 * @create: 2021-07-28 18:16
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.marlowe"})
@CrossOrigin
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class);
    }
}
