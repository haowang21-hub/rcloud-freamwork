package com.rcloud.auth.server.config;

import lombok.Data;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <Description> 非对称加密配置
 * 
 * @author wanghao
 * @CreateDate 2018年4月18日 下午1:38:57
 * @since V1.0
 * @see com.rcloud.auth.server.config
 */
@Configuration
@Data
public class KeyConfiguration {

    @Value("${jwt.secret}")
    private String secret;

    private byte[] pubKey;

    private byte[] priKey;

    private KeyPair keyPair;
}
