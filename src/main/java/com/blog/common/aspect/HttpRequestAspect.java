package com.blog.common.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.blog.common.utils.WebUtils;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;


/**
 * 切面，打印请求日志 {@link com.blog.common.interceptor.RequestPathInterceptor}
 *  <br>
 *      这个有些拦截不到. 还是用拦截器打印项目路径
 * @author zx
 * @date 2019/1/29
 */
@Slf4j
@Component
@Aspect
@Deprecated
public class HttpRequestAspect {

//    @Pointcut("execution(* com.back.controller.*.*.*(..))")
//    public void log() {}

//    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        String uri = request.getRequestURI();
        String ip = WebUtils.getIp(request);

        Map<String, String[]> params = request.getParameterMap();
        JSONObject json = new JSONObject();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            json.put(entry.getKey(), entry.getValue()[0]);
        }

        if (log.isInfoEnabled()) {
            log.info("request======={} {} {} {}", ip, request.getMethod(), uri, json.toString());
        }
    }

//    @After("log()")
    public void doAfter() {
        if (log.isInfoEnabled()) {
            //log.info("");
        }
    }

//    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        if (log.isInfoEnabled() && object !=null ) {
            log.info("response======={}", object.toString());
        }
    }

}
