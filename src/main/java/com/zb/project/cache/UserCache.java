package com.zb.project.cache;


import org.springframework.stereotype.Service;

import com.zb.project.util.EhcacheUtil;

/**
 * Created by Chengxs on 2017/7/3.
 */
@Service
public class UserCache {
    /**
     * 根据sessionID检查用户是否登录
     */
    public boolean checkLoginInfo(String sessionId) {
        Manager manager = (Manager) EhcacheUtil.getInstance().get(sessionId);
        return manager != null;
    }

    public void setLoginInfo(String sessionId, Manager manager) {
        EhcacheUtil.getInstance().put(sessionId, manager);
    }


    /**
     * 清空登录信息
     */
    public void clearLoginInfo(String sessionId) {
        EhcacheUtil.getInstance().remove(sessionId);
    }

    public Manager getLoginInfo(String sessionId) {
        return (Manager) EhcacheUtil.getInstance().get(sessionId);
    }
}
