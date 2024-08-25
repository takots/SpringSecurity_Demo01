package com.bobwu;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 启动SpringBoot 项目的入口程序
 */
@SpringBootApplication
@MapperScan("com.bobwu.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true) // 开启权限注解
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}