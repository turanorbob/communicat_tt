package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description
 * @Author legend <legendl@synnex.com>
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/20
 */
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudSessionRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudSessionRedisApplication.class, args);
    }
}
