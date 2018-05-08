package org.rcloud.springboot.web.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

/**
 * <Description> 请求、用户等信息写入MDC，方便后续日志记录
 * 
 * @author wanghao
 * @CreateDate 2018年3月16日 下午4:55:59
 * @since V1.0
 * @see org.rcloud.springboot.web.filter
 */
public class Slf4jMDCFilter extends AbstractRequestLoggingFilter {

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        MDC.put("beforeMessage", message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        MDC.clear();
    }

}
