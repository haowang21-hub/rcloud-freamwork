package com.rcloud.framework.common.exception;

/**
 * <Description> 异常处理
 * 
 * @author wanghao
 * @CreateDate 2018年3月26日 上午11:40:20
 * @since V1.0
 * @see com.rcloud.framework.common.exception
 */
public final class ExceptionHandler {

    public static void publish(Exception e) {
        throw new RuntimeException(e);
    }

    public static void publish(BusinessException e, String errorCode) {

    }

}
