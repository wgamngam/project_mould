package com.zb.project.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by tangshiwen on 2016/11/3.
 */

@Component   // 自定义权限框架标签注释，如果需要，则打开此标签。现在权限验证框架改为spring security实现
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new LoginInterceptor());
        // 配置拦截的路径
        ir.addPathPatterns("/api/**");
        // 配置不拦截的路径
        ir.excludePathPatterns("/upload/**","/**.jsp", "/**.htm", "/api/vestibule/**", "/api/login/staticLogin", "/api/login/logout", "/api/user/findbackpwd", "/api/user/changePwdByEmail");

        // 还可以在这里注册其它的拦截器
        //registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new OperationInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/upload/**","/**.jsp", "/**.htm", "/api/vestibule/**", "/api/login/staticLogin", "/api/login/logout", "/api/user/findbackpwd", "/api/user/changePwdByEmail");

        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/upload/**","/**.jsp", "/**.htm", "/api/vestibule/**", "/api/login/staticLogin", "/api/login/logout", "/api/user/findbackpwd", "/api/user/changePwdByEmail");
    }
}
