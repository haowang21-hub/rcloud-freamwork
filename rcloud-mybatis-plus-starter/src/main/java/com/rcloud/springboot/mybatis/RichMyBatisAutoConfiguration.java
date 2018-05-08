package com.rcloud.springboot.mybatis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <Description> 默认配置自动装载
 * 
 * @author wanghao
 * @CreateDate 2018年3月20日 上午11:40:02
 * @since V1.0
 * @see com.rcloud.springboot.mybatis
 */
@Configuration
@Import({
    MyBatisConfig.class
})
public class RichMyBatisAutoConfiguration {

}
