package com.rcloud.auth.server.service;

import java.util.Collections;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rcloud.auth.server.entity.Userinfo;
import com.rcloud.auth.server.entity.Users;

@Primary
@Service
public class UserService implements UserDetailsService {

    /**
     * Description: 用户校验
     * 
     * @author wanghao
     * @param username 用户名
     * @param password 密码
     * @return 如果验证成功返回用户信息，失败则返回null
     */
    public Userinfo validate(String username, String password) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = new Users();
        user.setUserAccount(username);
        user = user.selectOne(new EntityWrapper<>(user));
        if (user == null)
            throw new UsernameNotFoundException("No user found.");
        else
            return new User(user.getUserAccount(), user.getUserPwd(), true, true, true, true, Collections.emptySet());
    }
}
