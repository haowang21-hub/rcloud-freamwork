package com.rcloud.auth.server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rcloud.auth.common.util.jwt.IJWTInfo;
import com.rcloud.auth.common.util.jwt.JWTHelper;
import com.rcloud.auth.server.config.KeyConfiguration;

/**
 * <Description> 生成Jwt token工具类
 * 
 * @author wanghao
 * @CreateDate 2018年4月18日 下午2:28:07
 * @since V1.0
 * @see com.rcloud.auth.server.util
 */
@Component
public class JwtTokenUtil {

    @Value("${jwt.expire}")
    private int expire;

    @Autowired
    private KeyConfiguration keyConfiguration;

    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo, keyConfiguration.getPriKey(), expire);
    }

    public IJWTInfo getInfoFromToken(String token) throws Exception {
        return JWTHelper.getInfoFromToken(token, keyConfiguration.getPubKey());
    }

}
