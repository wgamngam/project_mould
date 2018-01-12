package com.zb.project.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.alibaba.fastjson.JSONObject;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
    		Object handler) throws Exception {
        logger.info("-------------------AuthInterceptor preHandle-------------");
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            Authentication authentication = ((HandlerMethod) handler)
            		.getMethodAnnotation(Authentication.class);

            //没有声明需要权限,或者声明不验证权限
            if (authentication == null || authentication.validate() == false) {
                return true;
            } else {
                //在这里实现自己的权限验证逻辑
                //请求的路径
                String contextPath = request.getContextPath();
                String url = request.getServletPath().toString();
                logger.info("----AuthInterceptor preHandle--contextPath:" + contextPath);
                logger.info("----AuthInterceptor preHandle--url:" + url);
                boolean flag = false;
                // 登录拦截器已经验证用户是否登陆，这里默认用户已经登录，直接读取用户信息
                String sessionID = request.getParameter(AppConstants.SESSIONID);

                if (flag) {//如果验证成功返回true
                    return true;
                } else {//如果验证失败
                    //根据请求类型区分返回，ajax请求返回json，http 请求返回jsp
                    if (request.getHeader("x-requested-with") != null
                            && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                    	//如果是ajax请求响应头会有x-requested-with
                       /* Map data_map = new HashMap();
                        data_map.put("code", 0);
                        data_map.put("msg", "权限不足，请联系管理员!");*/                        
            	    	JSONObject  codeObj = new JSONObject();
            	    	codeObj.put("code",ResponseCode.PERMISSION_REQUIRED.getCode());
            	    	codeObj.put("message","权限不足，请联系管理员!");
            	    	codeObj.put("data", AppConstants.NULL_OBJECT);	        
            	        String content = codeObj.toString();
                        //String data = JsonUtil.parse(data_map);
                        Tools.responseData(response, content);
                    } else {
                        //返回到权限不足提示页面
                        logger.error("权限不足！");
                        response.sendRedirect("/ad403");
                    }
                    return false;
                }
            }
        } else {
            return true;
        }
    }

}
