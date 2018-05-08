package com.rcloud.common.file.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <Description> 返回到前台的文件信息
 * 
 * @author wanghao
 * @CreateDate 2018年3月26日 上午10:38:09
 * @since V1.0
 * @see com.rcloud.common.file
 */
@Data
@SuppressWarnings("serial")
@ApiModel(value = "文件信息")
public class FileVO implements Serializable {

    @ApiModelProperty("文件标识")
    private String fileCd;

    /**
     * 文件名
     */
    @ApiModelProperty("文件名")
    private String fileName;

    /**
     * 文件大小 byte
     */
    @ApiModelProperty("文件大小")
    private long fileSize;

    /**
     * 文件访问URL
     */
    @ApiModelProperty("文件下载URL")
    private String url;

    /**
     * 文件保存路径
     */
    @ApiModelProperty("存储路径")
    private String storePath;

    /**
     * 文件后缀
     */
    @ApiModelProperty("文件后缀")
    private String fileSuffix;

    /**
     * 文件MD5值
     */
    @ApiModelProperty("文件MD5值")
    private String md5;

}
