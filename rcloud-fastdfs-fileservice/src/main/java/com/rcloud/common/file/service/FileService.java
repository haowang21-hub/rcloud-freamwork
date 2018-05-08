package com.rcloud.common.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rcloud.common.file.entity.FileVO;

/**
 * <Description> 文件操作接口
 * 
 * @author wanghao
 * @CreateDate 2018年3月26日 上午10:57:07
 * @since V1.0
 * @see com.rcloud.common.file
 */
public interface FileService {

    /**
     * Description: 前台单文件上传
     * 
     * @author wanghao
     * @param file 上传文件
     * @return
     */
    FileVO upload(MultipartFile file);

    /**
     * Description: 文件批量上传
     * 
     * @author wanghao
     * @param files 上传文件列表
     * @return
     */
    List<FileVO> upload(MultipartFile[] files);
    
}
