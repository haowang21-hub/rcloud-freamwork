package com.rcloud.auth.server.client;

import com.rcloud.auth.common.util.jwt.IJWTInfo;

/**
 * <Description> 客户端信息
 * 
 * @author wanghao
 * @CreateDate 2018年4月18日 下午1:33:24
 * @since V1.0
 * @see com.rcloud.auth.server
 */
@SuppressWarnings("serial")
public class ClientInfo implements IJWTInfo {

    private String clientId;

    private String name;

    private String id;

    public ClientInfo(String clientId, String name, String id) {
        this.clientId = clientId;
        this.name = name;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUniqueName() {
        return clientId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
