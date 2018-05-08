package com.rcloud.auth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <Description> 授权服务器启动引导类
 * 
 * @author wanghao
 * @CreateDate 2018年4月18日 下午1:51:21
 * @since V1.0
 * @see com.rcloud.auth.server
 */
@SpringBootApplication
@MapperScan("com.rcloud.auth.server.mapper")
public class RCloudAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RCloudAuthServerApplication.class, args);
    }
}
