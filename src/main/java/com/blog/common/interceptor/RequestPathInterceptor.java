package com.blog.common.interceptor;


import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.blog.common.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 请求日记拦截器
 *
 * @author zx
 * @date 2018/12/23
 */
@Slf4j
public class RequestPathInterceptor implements HandlerInterceptor {


    /**
     * 拦截器
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o) throws Exception {
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

        if (log.isInfoEnabled() && StringUtils.isNotBlank(uri)) {
            log.info("request <<<<<<<< ip:[{}] method:[{}] url:[{}]  object:[{}]", ip, request.getMethod(), uri, json.toString());
        }
        return true;
    }


    /**
     * controller 请求之后调用
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (log.isInfoEnabled() && modelAndView!=null ) {
            log.info("response >>>>>>>>> view:[{}] model:[{}]", modelAndView.getView() ,modelAndView.getModel());
        }
    }

    /**
     * 清理资源
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
