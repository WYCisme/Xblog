package com.blog.common.config.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.common.constants.AppConstants;
import com.blog.common.utils.JsonUtil;
import com.blog.model.bean.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.common.utils.WebUtils;

/**
 * 过滤器
 *
 * @author zx
 * @date 2019/3/31
 */
@Slf4j
public class MyShiroFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        // 获取请求token
        String token = getRequestToken((HttpServletRequest)request);
        log.info("  shiro token createToken " + token);
        if (StringUtils.isBlank(token)) {
            return null;
        }

        return new MyShiroToken(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest)request).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        return super.isAccessAllowed(request, response, mappedValue);
        // return false;
    }

    /**
     * 登录前调用的方法, 拦截的方法才会走
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 获取请求token，如果token不存在，直接返回401
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String token = getRequestToken(httpServletRequest);
        log.info("令牌是:{}", token);
        if (StringUtils.isBlank(token)) {

            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", WebUtils.getOrigin());
            // 令牌控制
            if (WebUtils.isAjax(httpServletRequest)) {
                httpResponse.getWriter().write(R.error(org.apache.http.HttpStatus.SC_UNAUTHORIZED, "no token").toString());
                String path = httpServletRequest.getContextPath();
                // ajax请求
                httpResponse.setHeader("sessionstatus", "TIMEOUT");
                httpResponse.setHeader("content_path", path + AppConstants.LOGIN);
            } else {
                httpServletRequest.setAttribute("message", "请先登录在访问网站");
                httpServletRequest.getRequestDispatcher(AppConstants.LOGIN).forward(httpServletRequest, httpResponse);
            }
            return false;
        }
        return executeLogin(request, response);
    }

    /**
     * 登录失败的情况
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
        ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", WebUtils.getOrigin());
        try {
            // 处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            R r = R.error(org.apache.http.HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());

            String json = JsonUtil.bean2Json(r);
            log.error("  shiro登录失败: " + json, e);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {

        }

        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        // 从header中获取token
        String token = httpRequest.getHeader("blog-token");

        // 如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("blog-token");
        }

        return token;
    }

}
