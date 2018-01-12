package com.zb.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.zb.project.mapper")
public class WebTransApplication extends SpringBootServletInitializer {

    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebTransApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebTransApplication.class, args);
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) { 			
                registry.addMapping("/**").allowedOrigins("http://mc.dev");
            	registry.addMapping("/**").allowedOrigins("*");
                registry.addMapping("/**").allowedHeaders("*");
            }
        };
    }

}
