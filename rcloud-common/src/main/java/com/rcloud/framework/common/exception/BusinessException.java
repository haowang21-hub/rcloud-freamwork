package com.rcloud.framework.common.exception;

/**
 * <Description> 业务异常定义， 每一种业务异常需要定义不同的异常编码(code)
 * 
 * @author wanghao
 * @CreateDate 2018年3月16日 下午2:20:40
 * @since V1.0
 * @see com.rcloud.framework.common.exception
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

    protected int code = 500;

    public BusinessException(String message) {
        this(message, 500);
    }

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
