package com.rcloud.auth.server.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <Description> 用户表
 * 
 * @author wanghao
 * @CreateDate 2018年4月18日 下午2:22:27
 * @since V1.0
 * @see com.rcloud.auth.server.entity
 */
@TableName("sys_users")
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

    @TableField("USER_ACCOUNT")
    private String userAccount;

    @TableField("USER_PWD")
    private String userPwd;

    @TableField("LAST_LOGIN")
    private Date lastLogin;

    @TableField("UP_TIME")
    private Date upTime;

    @TableField("NT")
    private String nt;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    @Override
    protected Serializable pkVal() {
        return this.userAccount;
    }

    @Override
    public String toString() {
        return "Users{" + ", userAccount=" + userAccount + ", userPwd=" + userPwd + ", lastLogin=" + lastLogin
            + ", upTime=" + upTime + ", nt=" + nt + "}";
    }
}
