package com.baidu.eduservice.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;


import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


/**
 * @author lw
 * @date 2020/11/27
 **/

@Configuration
@MapperScan(value = "com.baidu.eduservice.mapper")
//@EnableTransactionManagement 是spring-tx的注解  //开启事务管理器,对@Transactional进行管理   自动配置类中已经有了@EnableAutoConfiguration
public class MPConfiguration implements MetaObjectHandler {
    /*添加mp乐观锁*/
    //添加数据库版本号 修改一次更新一次版本号
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
//        return interceptor;
//    }
//    @Bean
//    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
//        return new OptimisticLockerInnerInterceptor();
//    }

    /**
     * 分页插件
     *
     * @return
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
//        // paginationInterceptor.setOverflow(false);
//        // 设置最大单页限制数量，默认 500 条，-1 不受限制
//        // paginationInterceptor.setLimit(500);
//        // 开启 count 的 join 优化,只针对部分 left join
////        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
//        return paginationInterceptor;
//    }
    @Override
    public void insertFill(MetaObject metaObject) {
        //实体类中属性名
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        //
        this.setFieldValByName("version", 1, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        this.setFieldValByName("createTime", new Date(), metaObject);  如果之前没有创建时间， 怎么加
        this.setFieldValByName("gmtModified", new Date(), metaObject);

    }

    /**
     * 逻辑删除插件    mybatis_plus 3.1 以上不需要插件    当前版本mybatis_plus 3.4.1  by 20201129
     * @param
     */
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }

    /**
     * SQL 执行性能分析插件
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     * dev：开发环境
     * test：测试环境
     * prod：生产环境
     */
    //3.1版本      3.4 已被移除    使用  p6spy插件
//    @Bean
//    @Profile({"dev", "test"})// 设置 dev test 环境开启
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        performanceInterceptor.setMaxTime(100);//ms，超过此处设置的ms则sql不执行
//        performanceInterceptor.setFormat(true);
//        return performanceInterceptor;
//    }
}
