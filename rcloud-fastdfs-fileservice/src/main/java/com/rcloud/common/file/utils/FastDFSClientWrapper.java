package com.rcloud.common.file.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

/**
 * <Description> FastDFS文件操作封装
 * 
 * @author wanghao
 * @CreateDate 2018年3月26日 上午10:13:03
 * @since V1.0
 * @see com.rcloud.common.file.utils
 */
@Component
public class FastDFSClientWrapper {

    private final Logger logger = LoggerFactory.getLogger(FastDFSClientWrapper.class);

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    public byte[] download(String fileCd) {
        StorePath storePath = StorePath.praseFromUrl("group1/M00/00/01/rBNkolq4u22AMzZ5AACgKIXkm9k670.jpg");
        return fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
    }

    /**
     * Description: 文件上传
     * 
     * @author wanghao
     * @param inStream 文件流
     * @param size 文件大小
     * @param suffix 后缀
     * @return
     */
    public StorePath upload(InputStream inStream, long size, String suffix) {
        return fastFileStorageClient.uploadFile(inStream, size, suffix, null);
    }

    /**
     * 将一段字符串生成一个文件上传
     *
     * @param content 文件内容
     * @param fileExtension
     * @return
     */
    public StorePath uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = fastFileStorageClient.uploadFile(stream, buff.length, fileExtension, null);
        return storePath;
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件访问地址
     * @return
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        }
        catch (FdfsUnsupportStorePathException e) {
            logger.warn(e.getMessage());
        }
    }

    public void downloadFile(String groupName, String storePath, final OutputStream outputStream) throws IOException {
        fastFileStorageClient.downloadFile(groupName, storePath, new DownloadCallback<OutputStream>() {
            @Override
            public OutputStream recv(InputStream inputStream) throws IOException {
                byte[] b = new byte[1024];
                int length;
                while ((length = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, length);
                }
                return outputStream;
            }
        });
        outputStream.close();
    }

}
