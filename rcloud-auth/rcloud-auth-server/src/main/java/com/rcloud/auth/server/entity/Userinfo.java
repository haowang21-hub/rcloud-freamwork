package com.rcloud.auth.server.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <Description> 用户信息实体
 * 
 * @author wanghao
 * @CreateDate 2018年4月18日 下午2:22:13
 * @since V1.0
 * @see com.rcloud.auth.server.entity
 */
@TableName("sys_userinfo")
public class Userinfo extends Model<Userinfo> {

    private static final long serialVersionUID = 1L;

    @TableField("USER_ACCOUNT")
    private String userAccount;
    @TableField("NICK_NAME")
    private String nickName;
    @TableField("EMAIL")
    private String email;
    @TableField("WECHAT_ACCOUNT")
    private String wechatAccount;
    @TableField("MOBILE")
    private String mobile;
    @TableField("USER_NAME")
    private String userName;
    @TableField("SEX")
    private String sex;
    @TableField("TEL")
    private String tel;
    @TableField("QQ")
    private String qq;
    @TableField("ADDRESS")
    private String address;
    @TableField("BIRTHDAY")
    private Date birthday;
    @TableField("FAX")
    private String fax;
    @TableField("USER_STATUS")
    private BigDecimal userStatus;
    @TableField("IS_USER")
    private BigDecimal isUser;
    @TableField("ADL_CD")
    private String adlCd;
    @TableField("ID_CARD")
    private String idCard;
    @TableField("TS")
    private Date ts;
    @TableField("NT")
    private String nt;
    @TableField("APP_ID")
    private String appId;
    @TableField("IS_ACTIVE")
    private BigDecimal isActive;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public BigDecimal getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(BigDecimal userStatus) {
        this.userStatus = userStatus;
    }

    public BigDecimal getIsUser() {
        return isUser;
    }

    public void setIsUser(BigDecimal isUser) {
        this.isUser = isUser;
    }

    public String getAdlCd() {
        return adlCd;
    }

    public void setAdlCd(String adlCd) {
        this.adlCd = adlCd;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public BigDecimal getIsActive() {
        return isActive;
    }

    public void setIsActive(BigDecimal isActive) {
        this.isActive = isActive;
    }

    @Override
    protected Serializable pkVal() {
        return this.userAccount;
    }

    @Override
    public String toString() {
        return "Userinfo{" + ", userAccount=" + userAccount + ", nickName=" + nickName + ", email=" + email
            + ", wechatAccount=" + wechatAccount + ", mobile=" + mobile + ", userName=" + userName + ", sex=" + sex
            + ", tel=" + tel + ", qq=" + qq + ", address=" + address + ", birthday=" + birthday + ", fax=" + fax
            + ", userStatus=" + userStatus + ", isUser=" + isUser + ", adlCd=" + adlCd + ", idCard=" + idCard + ", ts="
            + ts + ", nt=" + nt + ", appId=" + appId + ", isActive=" + isActive + "}";
    }
}
