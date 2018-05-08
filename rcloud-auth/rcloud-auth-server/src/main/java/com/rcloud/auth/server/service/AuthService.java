package com.rcloud.auth.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcloud.auth.common.util.jwt.JWTInfo;
import com.rcloud.auth.server.entity.Userinfo;
import com.rcloud.auth.server.util.JwtTokenUtil;

/**
 * <Description> 授权服务
 * 
 * @author wanghao
 * @CreateDate 2018年4月18日 下午2:32:57
 * @since V1.0
 * @see com.rcloud.auth.server.service
 */
@Service
public class AuthService {

    private JwtTokenUtil jwtTokenUtil;

    private UserService userService;

    /**
     * 构造方法注入
     * 
     * @param jwtTokenUtil
     * @param userService
     */
    @Autowired
    public AuthService(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Description: 用户登陆
     * 
     * @author wanghao
     * @param username 用户名
     * @param password 密码
     * @return JwtToken
     * @throws Exception
     */
    public String login(String username, String password) throws Exception {
        Userinfo user = userService.validate(username, password);
        String token = "";
        if (user != null) {
            token = jwtTokenUtil.generateToken(new JWTInfo(user.getUserName(), user.getUserAccount(), user.getUserName()));
        }
        return token;
    }

    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    public Boolean invalid(String token) {
        return null;
    }

    public String refresh(String oldToken) {
        return null;
    }
}
