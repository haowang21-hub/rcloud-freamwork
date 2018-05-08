package com.rcloud.common.file.entity;

import com.baomidou.mybatisplus.enums.IdType;

import lombok.Builder;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <Description> 文件实体
 * 
 * @author wanghao
 * @CreateDate 2018年3月26日 下午2:57:54
 * @since V1.0
 * @see com.rcloud.common.file.entity
 */
@Builder
@TableName("T_BUSI_FILE_STORE")
public class FileStore extends Model<FileStore> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 文件编码
     */
    @TableField("file_cd")
    private String fileCd;

    /**
     * 文件名
     */
    @TableField("file_name")
    private String fileName;
    /**
     * 文件大小 byte
     */
    @TableField("file_size")
    private Integer fileSize;
    /**
     * 文件后缀
     */
    @TableField("file_suffix")
    private String fileSuffix;
    /**
     * 文件md5值
     */
    private String md5;
    /**
     * 分组名
     */
    @TableField("group_name")
    private String groupName;
    /**
     * 文件路径
     */
    @TableField("store_path")
    private String storePath;
    /**
     * 文件类型（txt, jpg, doc, xls, ppt） 所有的图片都是jpg
     */
    @TableField("file_type")
    private String fileType;
    /**
     * 业务编码
     */
    @TableField("busi_code")
    private String busiCode;
    /**
     * 应用id
     */
    @TableField("appid")
    private String appid;
    /**
     * 创建时间
     */
    @TableField("crt_dt")
    private Date crtDt;
    /**
     * 创建用户
     */
    @TableField("user_id")
    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getFileCd() {
        return fileCd;
    }

    public void setFileCd(String fileCd) {
        this.fileCd = fileCd;
    }

    @Override
    public String toString() {
        return "FileStore [id=" + id + ", fileCd=" + fileCd + ", fileName=" + fileName + ", fileSize=" + fileSize
            + ", fileSuffix=" + fileSuffix + ", md5=" + md5 + ", groupName=" + groupName + ", storePath=" + storePath
            + ", fileType=" + fileType + ", busiCode=" + busiCode + ", appid=" + appid + ", crtDt=" + crtDt
            + ", userId=" + userId + "]";
    }

}
