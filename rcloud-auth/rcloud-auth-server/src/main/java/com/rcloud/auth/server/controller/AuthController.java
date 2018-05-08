package com.rcloud.auth.server.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.rcloud.auth.server.service.AuthService;
import com.rcloud.auth.server.web.model.AuthorizationRequest;
import com.rcloud.auth.server.web.model.JwtAuthenticationRequest;
import com.rcloud.auth.server.web.model.JwtAuthenticationResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/oauth")
@SessionAttributes("authorizationRequest")
public class AuthController {

    private String userLoginPage = "forward:/oauth/login";

    private String errorPage = "forward:/oauth/error";

    @Autowired
    private AuthService authService;

    /**
     * Description: 认证 <br/>
     * 1. 判断客户端是否合法 <br/>
     * 2. 校验scope <br/>
     * 3. 如果是授权码验证，跳转到登陆页面 <br/>
     * 4. 如果是token验证，跳转到登陆页面 <br/>
     * 
     * @author wanghao
     * @return
     */
    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public ModelAndView authorize(AuthorizationRequest authorizationRequest, SessionStatus sessionStatus) {

        try {
            return getUserLoginPageResponse(authorizationRequest);
        }
        catch (ReflectiveOperationException e) {
        }
        return null;
    }

    private ModelAndView getUserLoginPageResponse(AuthorizationRequest authorizationRequest) throws ReflectiveOperationException {
        Map<String, ?> model = new HashMap<>();
        try {
            BeanUtils.populate(authorizationRequest, model);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return new ModelAndView(userLoginPage, model);
    }

    /**
     * Description: 登陆获取token
     * 
     * @author wanghao
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> authToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    /**
     * Description: 刷新token
     * 
     * @author wanghao
     * @param request
     * @return
     */
    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    /**
     * Description: 验证token
     * 
     * @author wanghao
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public ResponseEntity<?> verify(String token) throws Exception {
        authService.validate(token);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "invalid", method = RequestMethod.POST)
    public ResponseEntity<?> invalid(String token) {
        authService.invalid(token);
        return ResponseEntity.ok(true);
    }
}
