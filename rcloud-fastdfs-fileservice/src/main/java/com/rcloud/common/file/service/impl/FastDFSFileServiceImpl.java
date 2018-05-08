package com.rcloud.common.file.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.StorePath;
import com.rcloud.common.file.entity.FileStore;
import com.rcloud.common.file.entity.FileVO;
import com.rcloud.common.file.service.FileService;
import com.rcloud.common.file.utils.FastDFSClientWrapper;
import com.rcloud.framework.common.exception.BusinessException;
import com.rcloud.framework.common.id.IDGenerator;
import com.rcloud.framework.common.utils.StringUtils;
import com.rcloud.springboot.datasource.TargetDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * <Description> FastDFS文件操作实现
 * 
 * @author wanghao
 * @CreateDate 2018年3月26日 上午11:02:44
 * @since V1.0
 * @see com.rcloud.common.file.service
 */
@Slf4j
@Component
@ConditionalOnBean(FastDFSClientWrapper.class)
@TargetDataSource("file")
public class FastDFSFileServiceImpl implements FileService {

    @Autowired
    FastDFSClientWrapper fastDfsClient;

    @Autowired
    FdfsWebServer fdfsConfig;

    public static String FDFS_WEB_SERVER = "";

    @Override
    public FileVO upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String suffix = FilenameUtils.getExtension(filename);
        Long size = file.getSize();
        String md5 = null;
        // 生成UUID
        String fileCd = IDGenerator.UUID.generate();

        StorePath storePath = null;
        try {
            // 保存文件MD5值 便于以后做文件排重
            md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
            storePath = fastDfsClient.upload(file.getInputStream(), file.getSize(), suffix);
        }
        catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("文件上传失败。", e);
        }
        if (StringUtils.isEmpty(FDFS_WEB_SERVER)) {
            FDFS_WEB_SERVER = fdfsConfig.getWebServerUrl();
            if (!FDFS_WEB_SERVER.endsWith("/")) {
                FDFS_WEB_SERVER += "/";
            }
        }

        String url = FDFS_WEB_SERVER + storePath.getFullPath();

        // 保存文件信息
        FileStore filStore = FileStore.builder().fileName(filename).fileSize(size.intValue()).fileSuffix(suffix)
            .storePath(storePath.getFullPath()).groupName(storePath.getGroup()).fileCd(fileCd).md5(md5)
            .crtDt(new Date()).build();
        filStore.insert();

        FileVO fileVO = new FileVO();
        BeanUtils.copyProperties(filStore, fileVO);
        fileVO.setUrl(url);
        return fileVO;
    }

    @Override
    public List<FileVO> upload(MultipartFile[] files) {
        List<FileVO> uploadList = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            uploadList.add(upload(multipartFile));
        }
        return uploadList;
    }

}
