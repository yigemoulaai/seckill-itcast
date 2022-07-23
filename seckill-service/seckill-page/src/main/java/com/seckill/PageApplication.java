package com.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*****
 * @Author: lichuang
 * @Description: com.seckill.PageApplication
 ****/
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.seckill.goods.feign"})
public class PageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageApplication.class,args);
    }
}
