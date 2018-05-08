package com.rcloud.auth.common.util.jwt;

import java.io.Serializable;

/**
 * <Description>
 * 
 * @author wanghao
 * @CreateDate 2018年4月18日 下午1:26:00
 * @since V1.0
 * @see com.rcloud.auth.common.util.jwt
 */
public interface IJWTInfo extends Serializable{

    /**
     * 获取用户名
     * 
     * @return
     */
    String getUniqueName();

    /**
     * 获取用户ID
     * 
     * @return
     */
    String getId();

    /**
     * 获取名称
     * 
     * @return
     */
    String getName();
}
