package com.rcloud.springboot.mybatis;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;

/**
 * <Description> MyBatis配置
 * 
 * @author wanghao
 * @CreateDate 2018年3月20日 上午11:39:18
 * @since V1.0
 * @see com.rcloud.springboot.mybatis
 */
@Configuration
@ConditionalOnBean(DataSource.class)
public class MyBatisConfig {

    /**
     * Description: MyBatis-Plus SQL执行效率插件【只适用于开发环境】
     * 
     * @author wanghao
     * @return
     */
    @Bean
    @Profile(value = "dev")
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * Description: MyBatis-Plus 分页插件
     * 
     * @author wanghao
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}
