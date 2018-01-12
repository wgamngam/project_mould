package com.zb.project.util;


public enum ResponseCode {
    OK(10000, "OK"),
    PARAM_ERROR(20000, "参数错误"),
    SESSION_INVALID(30000, "会话失效,或用户未登录"),
    NOT_LOGIN(30001, "用户未登录"),
    LOGIN_FAILED(30008, "登录失败"),
    LOGIN_NOT_ALLOWED(30009, "登录失败，用户被禁用"),
    LOGIN_INVALID(30010, "登录失败，密码错误"),
    ACCOUNT_STATUSERROR(30012, "登录失败，账号角色选择错误"),
    PWD_ERROR(30013, "密码错误"),
    INVALID_CODE(30014, "验证码错误"),
    EMAIL_NOTACTIVATE(30015, "邮箱未激活"),
    EMAIL_ACTIVATED(30016, "邮箱已被激活"),
    EMAIL_SALT_ERROR(30017, "邮箱激活秘钥错误"),
    EMAIL_SALT_OUT(30018, "邮箱激活秘钥过时"),
    EMAIL_REPETITIVE(30019, "更改邮箱与原邮箱重复"),
    EMAIL_USERNAME_ERROR(30020, "用户名和邮箱均不存在"),
    PWDCHANGE_SALT_ERROR(30021, "密码修改秘钥错误"),
    PWDCHANGE_SALT_OUT(30022, "密码修改秘钥过时"),
	REGISTER_FAILED(30023,"注册失败"),
    PERMISSION_REQUIRED(40000, "没有操作权限"),


    LOGIN_REQUIRED(40001, "登录超时，请重新登录"),

    CONFLICT(41000, "对象状态已发生变化"),
    NOT_FOUND(50000, "内容未找到"),

    INTERNAL_ERROR(60000, "服务器内部错误"),
    UNKNOWN_ERROR(70000, "未知错误,请稍后再试"),
    FILEUPLOAD_NOT_EXISTS(70001, "上传文件不存在"),
    FILEDOWNLOAD_NOT_EXISTS(70002, "下载文件不存在"),
    FILEDOWNLOAD_ERROR(70003, "下载文件失败"),
    IMAGE_ERROR(70004, "图片获取失败"),
    SYSTEM_ERROR(90000, "系统内部错误"),
    ILLEGAL_OPERATION(80000, "非法操作");
	


    private int code;
    private String name;

    ResponseCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOK() {
        return this.getCode() == OK.getCode();
    }
}
