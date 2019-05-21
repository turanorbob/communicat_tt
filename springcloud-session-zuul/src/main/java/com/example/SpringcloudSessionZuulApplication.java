package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
public class SpringcloudSessionZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudSessionZuulApplication.class, args);
    }
}
