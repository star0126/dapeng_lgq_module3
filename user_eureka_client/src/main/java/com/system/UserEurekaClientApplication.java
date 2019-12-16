package com.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: dapeng_lgq_module3
 * @since:JDK-1.8
 * @author: Star-GuoqingLi
 * @create: 2019-12-16 23:05
 * @version:第1版
 * @description:
 **/
@SpringBootApplication
@EnableEurekaClient
public class UserEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserEurekaClientApplication.class,args);
    }
}
