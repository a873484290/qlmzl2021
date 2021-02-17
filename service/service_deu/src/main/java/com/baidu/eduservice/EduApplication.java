package com.baidu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lw
 * @date 2020/11/30
 **/

@SpringBootApplication
//跨模块扫描包   依赖中引用，扫描包
@ComponentScan(basePackages = {"com.baidu"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }

}
