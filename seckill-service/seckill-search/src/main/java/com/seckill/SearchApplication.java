package com.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/*****
 * @Author: lichuang
 * @Project: seckillproject
 * @Description: com.seckill.SearchApplication
 ****/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//扫描Dao接口
@EnableElasticsearchRepositories(basePackages = {"com.seckill.search.dao"})
//Feign接口
@EnableFeignClients(basePackages = {"com.seckill.goods.feign"})
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }
}
