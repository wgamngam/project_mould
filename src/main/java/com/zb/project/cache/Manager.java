package com.zb.project.cache;



import java.io.Serializable;
import java.util.Date;


import com.zb.project.util.AppConstants;



public class Manager implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 195789152426139567L;
	private long id;
    private String username;//登录名
    private String name;//真实姓名
    private String password;//密码
    private String salt;//加密值
    private int status;//状态 1正常  0已删除
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private String email;//邮箱地址
    private String emailSalt;//绑定邮箱唯一标示
    private Date emailSaltTime;//绑定邮箱唯一标示过期时间
    private Integer emalStatus = AppConstants.EMAIL_STATUS_NOTACTIVATE;//密保邮箱状态 1:已验证 0未验证
    private String pwdChangeSalt;//修改密码唯一标示
    private Date pwdChangeTime;//修改密码过期时间
 


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailSalt() {
        return emailSalt;
    }

    public void setEmailSalt(String emailSalt) {
        this.emailSalt = emailSalt;
    }

    public Date getEmailSaltTime() {
        return emailSaltTime;
    }

    public void setEmailSaltTime(Date emailSaltTime) {
        this.emailSaltTime = emailSaltTime;
    }

    public int getEmalStatus() {
        return emalStatus;
    }

    public void setEmalStatus(Integer emalStatus) {
        this.emalStatus = emalStatus;
    }

    public String getPwdChangeSalt() {
        return pwdChangeSalt;
    }

    public void setPwdChangeSalt(String pwdChangeSalt) {
        this.pwdChangeSalt = pwdChangeSalt;
    }

    public Date getPwdChangeTime() {
        return pwdChangeTime;
    }

    public void setPwdChangeTime(Date pwdChangeTime) {
        this.pwdChangeTime = pwdChangeTime;
    }

    public Manager() {
    }

    public Manager(long id) {
        this.id = id;
    }


    public Manager(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }


}
