package com.springboot.jwtdemo2.config;

import com.springboot.jwtdemo2.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 注册验证tocken的拦截器
 * @Author 傅琦
 * @Date 2019/4/13 20:41
 * @Version V1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/user/whoami");
    }
}
