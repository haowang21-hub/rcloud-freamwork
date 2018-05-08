package com.rcloud.springboot.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <Description> 代理带有@TargetDataSource注解的类和方法， 自动切换数据源 <br />
 * WARN: 目前不支持父类方法代理， 所以继承BaseService的类如果要切换数据源，调用的方法必须要重写。!
 * 
 * @author wanghao
 * @CreateDate 2018年3月13日 上午11:34:40
 * @since V1.0
 * @see com.rcloud.springboot.datasource
 */
// 该切面应当先于 @Transactional 执行
@Order(-1)
@Aspect
@Component
public class DynamicDataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("@annotation(tds)")
    public void pointcutA(TargetDataSource tds) {
    }

    @Pointcut("@within(tds)")
    public void pointcutC(TargetDataSource tds) {

    }

    @Before("pointcutA(tds) || pointcutC(tds)")
    public void switchDataSource(JoinPoint point, TargetDataSource tds) {
        if (!DynamicDataSource.containDataSourceKey(tds.value())) {
            logger.warn("DataSource [{}] doesn't exist ", tds.value());
        }
        else {
            // 切换数据源
            DynamicDataSource.setDataSourceKey(tds.value());
            logger.info("Switch DataSource to [{}] in method [{}] ", DynamicDataSource.getDataSourceKey(),
                point.getSignature());
        }
    }

    @After("pointcutA(tds) || pointcutC(tds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource tds) {
        // 将数据源还原为默认数据源
        DynamicDataSource.clearDataSourceKey();
        logger.info(
            "Restore DataSource to [{}] in method [{}] " + DynamicDataSource.getDataSourceKey() + point.getSignature());
    }
}
