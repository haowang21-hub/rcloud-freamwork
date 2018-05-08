package com.rcloud.auth.server.web.model;

/**
 * <Description> 认证请求实体
 * 
 * @author wanghao
 * @CreateDate 2018年4月18日 下午6:54:01
 * @since V1.0
 * @see com.rcloud.auth.server.web.model
 */
public class AuthorizationRequest {

    /**
     * 授权类型 code:授权码，token：令牌
     */
    private String responseType;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 重定向的URI
     */
    private String redirectUri;

    /**
     * 权限范围
     */
    private String scope;

    /**
     * 客户端的当前状态,原封不动返回
     */
    private String state;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
