package com.zb.project.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	  private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 预处理回调方法，实现处理器的预处理（如登录检查）。
     * 第三个参数为响应的处理器，即controller。
     * 返回true，表示继续流程，调用下一个拦截器或者处理器。
     * 返回false，表示流程中断，通过response产生响应。
     */
    @SuppressWarnings("rawtypes")
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        logger.info("-------------------preHandle-------------");
        // 验证用户是否登陆
        String sessionID = request.getParameter(AppConstants.SESSIONID);
        if (false /*这里自己判断逻辑*/) {
        //根据请求类型区分返回，ajax请求返回json，http 请求返回jsp
        logger.info("-------------------用户未登录 或会话已失效-------------");                  
	    JSONObject  codeObj = new JSONObject();
	    codeObj.put("code",ResponseCode.SESSION_INVALID.getCode());
	    codeObj.put("message",ResponseCode.SESSION_INVALID.getName());
	    codeObj.put("data", AppConstants.NULL_OBJECT);	        
        String content = codeObj.toString();
        Tools.responseData(response, content);
        //失败请求内容显示
        String ipAddr = Tools.getIpAddr(request);
        String requestUrl = request.getServletPath();
        @SuppressWarnings("unchecked")
		Map<String, String[]> paramMap = request.getParameterMap();
		Iterator iterator = paramMap.entrySet().iterator();
        String param = "";
        while (iterator.hasNext()) {
            {
                Map.Entry e = (Map.Entry) iterator.next();
                String key = (String) e.getKey();
                String[] values = (String[]) e.getValue();
                String value = "";
                if (values != null && values.length > 0) {
                    for (int i = 0; i < values.length; i++) {
                        value = value + values[i] + " ";
                    }
                }
                value = value.substring(0, value.length() - 1);
                param = param + key + "=" + value + "&";
            }
        }
        if (param != null && param.length() > 0) {
            param = param.substring(0, param.length() - 1);
        }
        logger.info("request data: ipAddress=" + ipAddr + ","
        		+ "requestUrl=" + requestUrl + ",parameters=[" + param + "]");
        return false;
     }
        return true;
    }

    /**
     * 当前请求进行处理之后，也就是Controller 方法调用之后执行，
     * 但是它会在DispatcherServlet 进行视图返回渲染之前被调用。
     * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("-------------------postHandle-------------");
    }

    /**
     * 方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。
     * 这个方法的主要作用是用于进行资源清理工作的。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        logger.info("-------------------afterCompletion-------------");
    }

}
