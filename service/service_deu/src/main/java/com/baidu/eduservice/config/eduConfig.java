package com.baidu.eduservice.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 * @date 2020/11/30
 **/

@Configuration
@MapperScan("com.baidu.eduservice.mapper")
//配置类
@ComponentScan(basePackages = {"com.baidu"})
public class eduConfig {


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
