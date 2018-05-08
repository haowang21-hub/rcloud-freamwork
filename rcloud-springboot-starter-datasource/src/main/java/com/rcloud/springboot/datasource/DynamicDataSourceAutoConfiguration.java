package com.rcloud.springboot.datasource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * <Description> 动态数据源自动装载、
 * 
 * @author wanghao
 * @CreateDate 2018年3月12日 下午2:16:19
 * @since V1.0
 * @see com.rich.framework.datasource
 */
@Configuration
@Import({
    DynamicDataSourceAspect.class
})
public class DynamicDataSourceAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceAutoConfiguration.class);

    private static String DEFAULT_DATASOURCE_ID = "default";

    @Bean
    public DynamicDataSourceRepository dynamicDataSourceRepository() {
        return new DynamicDataSourceRepository();
    }

    /**
     * Description: 根据配置自动创建数据源
     * 
     * @author wanghao
     * @return
     */
    @Bean
    @Primary
    @Qualifier("dataSource")
    @ConditionalOnMissingBean(DynamicDataSource.class)
    public DataSource dataSource(DynamicDataSourceRepository dynamicDataSourceRepository) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        for (Entry<String, DruidDbProperties> entry : dynamicDataSourceRepository.getDatasource().entrySet()) {
            DynamicDataSource.dataSourceKeys.add(entry.getKey());

            DataSource ds = buildDataSource(entry.getValue());
            targetDataSources.put(entry.getKey(), ds);
        }
        if (targetDataSources.containsKey(DEFAULT_DATASOURCE_ID)) {
            dynamicDataSource.setDefaultTargetDataSource(targetDataSources.get(DEFAULT_DATASOURCE_ID));
        }
        // 如果只配置了一个数据源则为默认数据源
        else if (targetDataSources.size() == 1) {
            for (Entry<Object, Object> entry : targetDataSources.entrySet()) {
                dynamicDataSource.setDefaultTargetDataSource(entry.getValue());
            }
        }
        else {
            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }

        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    /**
     * Description: 创建DataSource
     * 
     * @author wanghao
     * @param config 数据源配置
     * @return
     */
    public DataSource buildDataSource(DruidDbProperties properties) {
        if (StringUtils.isEmpty(properties.getUrl())) {
            logger.error("Your database connection pool configuration is incorrect, Please check your Spring profile");
            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }

        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(properties.getUrl());
        datasource.setUsername(properties.getUsername());
        datasource.setPassword(properties.getPassword());
        datasource.setInitialSize(properties.getInitialSize());
        datasource.setMinIdle(properties.getMinIdle());
        datasource.setMaxActive(properties.getMaxActive());
        datasource.setMaxWait(properties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(properties.getValidationQuery());
        datasource.setTestWhileIdle(properties.isTestWhileIdle());
        datasource.setTestOnBorrow(properties.isTestOnBorrow());
        datasource.setTestOnReturn(properties.isTestOnReturn());
        try {
            datasource.setFilters(properties.getFilters());
        }
        catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(properties.getConnectionProperties());
        return datasource;
    }
}
