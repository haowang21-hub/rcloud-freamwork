package com.rcloud.framework.common.http;

import org.springframework.http.HttpStatus;

import com.rcloud.framework.common.exception.BusinessException;

/**
 * <Description> 后台返回到前台的统一数据结构
 * 
 * @author wanghao
 * @CreateDate 2018年3月16日 下午5:02:54
 * @since V1.0
 * @see org.rcloud.springboot.web.http
 */
public class ResponseMessage<T> {

    /**
     * 错误码，每个自定义异常都有单独的异常码
     */
    private int code;

    /**
     * 标识请求是否成功
     */
    private boolean success = true;

    /**
     * 返回消息
     */
    private Object message;

    /**
     * 返回的结果
     */
    private T body;

    /**
     * 返回数据时间戳
     */
    private long timestamp;

    public static <T> ResponseMessage<T> ok(T body) {
        ResponseMessage<T> response = new ResponseMessage<>();
        response.message = HttpStatus.OK.getReasonPhrase();
        response.code = HttpStatus.OK.value();
        response.body = body;
        response.timestamp = System.currentTimeMillis();
        return response;
    }

    public static <T> ResponseMessage<T> error(int code, Object message) {
        ResponseMessage<T> response = new ResponseMessage<>();
        response.message = message;
        response.code = code;
        response.success = false;
        response.timestamp = System.currentTimeMillis();
        return response;
    }

    public static <T> ResponseMessage<T> error(Exception e) {
        if (e instanceof BusinessException) {
            return error((BusinessException) e);
        }
        ResponseMessage<T> response = new ResponseMessage<>();
        response.message = e.getMessage();
        response.code = 500;
        response.success = false;
        response.timestamp = System.currentTimeMillis();
        return response;
    }

    public static <T> ResponseMessage<T> error(BusinessException e) {
        ResponseMessage<T> response = new ResponseMessage<>();
        response.message = e.getMessage();
        response.code = e.getCode();
        response.success = false;
        response.timestamp = System.currentTimeMillis();
        return response;
    }

    public int getCode() {
        return code;
    }

    public Object getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
