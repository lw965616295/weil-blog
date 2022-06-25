package com.weil.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName BlogApp
 * @Author weil
 * @Description //启动类
 * @Date 2021/6/10 13:53
 * @Version 1.0.0
 **/
@SpringBootApplication
@MapperScan("com.weil.blog.mapper")
@EnableCaching
public class BlogApp {
    public static void main(String[] args) {
        SpringApplication.run(BlogApp.class, args);
    }
}
