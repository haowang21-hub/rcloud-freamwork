package com.rcloud.framework.common.exception;

/**
 * <Description> 数据校验异常
 * 
 * @author wanghao
 * @CreateDate 2018年3月20日 下午1:39:56
 * @since V1.0
 * @see com.rcloud.framework.common.exception
 */
public class ValidateException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ValidateException(String message) {
        super(message, 520);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
        this.code = 520;
    }
}
