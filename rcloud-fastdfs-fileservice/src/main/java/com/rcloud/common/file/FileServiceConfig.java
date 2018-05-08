package com.rcloud.common.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

import com.github.tobato.fastdfs.FdfsClientConfig;

/**
 * <Description> 启用文件管理组件
 * 
 * @author wanghao
 * @CreateDate 2018年3月26日 下午3:47:48
 * @since V1.0
 * @see com.rcloud.common.file
 */
@Configuration
@ComponentScan
@MapperScan("com.rcloud.common.file.mapper*")
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FileServiceConfig {

}
