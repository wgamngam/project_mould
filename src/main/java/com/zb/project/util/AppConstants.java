package com.zb.project.util;


public class AppConstants {

    public static final String SYSTEM_ADMIN_MAP = "admin";//内存用户信息KEY
    public static final String SYSTEM_ADMIN_MANAGER = "manager";//用户信息KEY
    public static final String SYSTEM_ADMIN_SESSION = "SessionId";//登录身份KEY
    //超级管理员用户名
    public static final String SYSTEM_ADMIN_USERNAME = "admin";//c超级管理员

    public static final String SESSIONID = "SessionId";

    //web端默认返回值
    public static final String NULL_OBJECT ="null";
    
    //web端默认返回值
    public static final String JSON_CODE ="code";
    
    public static final String JSON_MESSAGE ="message";
    
    public static final String JSON_DATA ="data";
    
    public static final String JSON_TOTAL ="total";

    
    //Netty 消息map默认value值
    public static final String JSON_MSGQUEUE_DEFAULT = "value";

    //系统顶级菜单父ID
    public static final Long SYSTEM_MENU_ROOT = 0l;


    public static final Integer EMAIL_STATUS_ACTIVATE = 1;//邮箱已激活
    public static final Integer EMAIL_STATUS_NOTACTIVATE = 0;//邮箱未激活

    //管理员/用户状态
    public static final int MANAGER_STATUS_NORMAL = 1;//正常
    public static final int MANAGER_STATUS_DEL = 0;//已经删除

    //分页列表默认开始，默认条数
    public static final int PAGE_DEFAULT_START = 0;//默认起始
    public static final int PAGE_DEFAULT_SIZE = 10;//默认条数

    public static final Long PERMISSION_GROUP_NORMAL = 1L;//普通用户
    public static final Long PERMISSION_GROUP_DATABANK = 2L;//数据银行用户
    public static final Long PERMISSION_GROUP_ADMIN = 3L;//管理员用户

    public static final Integer ENTERPRISE_ORIGIN = 0;//未提交
    public static final Integer ENTERPRISE_AUDIT = 1;//待审核
    public static final Integer ENTERPRISE_PASS = 2;//已通过
    public static final Integer ENTERPRISE_REJECT = 3;//已驳回



    public static final String ID_MULTI_SPLIT = ",";//多个ID分隔符

    //文件上传目录
    public static final String FILE_UPLOAD_DIR = "upload/resource";


    //1：正序 2：倒序
    public static final int POSITIVE = 1;
    public static final int FLASHBACK = 2;

    //系统生成excel文件后缀
    public static final String SYSTEM_FILEUPLOAD_POSTFIX_EXCEL = ".xls";

    public static final Double DOUBLE_ZERO = 0.0;

    public static final int RANKSIZE = 3;


    //文件状态 1生成成功 2生成中 3生成失败
    public static final int EXPORTTYPE_SUC = 1;
    public static final int EXPORTTYPE_IN = 2;
    public static final int EXPORTTYPE_FAIL = 3;

    //导出文件每个sheet行数
    public static final int EXPORTFILE_LENTH = 50000;
}
