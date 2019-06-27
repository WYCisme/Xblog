package com.blog.common.interceptor;

import com.blog.common.constants.AppConstants;
import com.blog.common.constants.SystemConstants;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 后端拦截器
 *
 * @author zx
 * @date 2019/1/28
 */
@SpringBootConfiguration
public class BackInterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if(SystemConstants.ISLOCAL) {
            registry.addInterceptor(new RequestPathInterceptor()).addPathPatterns("/**").excludePathPatterns("/back/**","/static/**");
        }
//        registry.addInterceptor(new AuthorizedInterceptor()).addPathPatterns("/**").excludePathPatterns(AuthorizedInterceptor.IGNORE_URL).excludePathPatterns("/static/**","/layui/**");
    }
}
