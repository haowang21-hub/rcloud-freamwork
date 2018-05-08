package com.rcloud.auth.server.runner;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.rcloud.auth.common.util.jwt.RsaKeyHelper;
import com.rcloud.auth.server.config.KeyConfiguration;

/**
 * <Description> auth server启动后初始化密钥以及公钥 <br />
 * 先从Redis读取密钥以及公钥信息 ， 如果redis没有初始化则生成密钥、公钥
 * 
 * @author wanghao
 * @CreateDate 2018年3月22日 上午11:21:45
 * @since V1.0
 * @see com.github.wxiaoqi.security.auth.runner
 */
@Configuration
public class AuthServerRunner implements CommandLineRunner {

    /**
     * JWT签名私钥
     */
    private static final String REDIS_PRI_KEY = "RICHWAY:AUTH:JWT:PRI";

    /**
     * JWT签名公钥
     */
    private static final String REDIS_PUB_KEY = "RICHWAY:AUTH:JWT:PUB";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private KeyConfiguration keyConfiguration;

    @Override
    public void run(String... args) throws Exception {
        if (redisTemplate.hasKey(REDIS_PRI_KEY) && redisTemplate.hasKey(REDIS_PUB_KEY)) {
            keyConfiguration.setPriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_PRI_KEY).toString()));
            keyConfiguration.setPubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_PUB_KEY).toString()));
        }
        else {
            Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(keyConfiguration.getSecret());
            keyMap = RsaKeyHelper.generateKey(keyConfiguration.getSecret());
            keyConfiguration.setPriKey(keyMap.get("pri"));
            keyConfiguration.setPubKey(keyMap.get("pub"));
            redisTemplate.opsForValue().set(REDIS_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
            redisTemplate.opsForValue().set(REDIS_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));

        }
    }
}
