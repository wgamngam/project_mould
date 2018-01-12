package com.zb.project.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;


public class OperationInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(OperationInterceptor.class);


    /**
     * 管理员访问拦截器。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
    	
        logger.info("-------------------manager log interceptor-------------");
        String sessionID = request.getParameter(AppConstants.SESSIONID);
     
        if (false/*自己判断登录逻辑*/) {
            logger.info("{\"code\": 0,\"msg\": \"用户未登录!\"}");
//            response.sendRedirect("/");
            return false;
        } else {
            String ipAddr = Tools.getIpAddr(request);
            String requestUrl = request.getServletPath();
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
            logger.info("request data: ipAddress=" + ipAddr + ",requestUrl=" + requestUrl + ",parameters=[" + param + "]");

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

    }
}
