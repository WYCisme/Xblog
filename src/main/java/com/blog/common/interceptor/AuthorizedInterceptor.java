package com.blog.common.interceptor;


import com.blog.model.entity.Admin;
import com.blog.common.utils.WebUtils;
import com.blog.common.constants.AppConstants;
import com.blog.model.bean.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器
 * 这里使用shiro 此类不需要
 * @{link com.back.common.config.shiro.ShiroConfig}
 *
 * @author zx
 * @date 2018/12/23
 */
@Deprecated
@Slf4j
public class AuthorizedInterceptor implements HandlerInterceptor {


    /** 忽略的请求URL **/
    public static final String[] IGNORE_URL = {AppConstants.LOGIN
            ,"/back/admin/login"
            ,"/login"
            ,"/global/captcah"
            ,"/404"};

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


        boolean flag = false;
        String servletPath = httpServletRequest.getServletPath();
        for (String s : IGNORE_URL) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            Admin admin = (Admin) httpServletRequest.getSession().getAttribute(AppConstants.USER_SESSION);
            if (admin == null) {
                if(WebUtils.isAjax(httpServletRequest)){
                    httpServletResponse.getWriter().write(R.error("请先登录在访问网站").toString());
                    return flag;
                }else {
                    httpServletRequest.setAttribute("message", "请先登录在访问网站");
                    httpServletRequest.getRequestDispatcher(AppConstants.LOGIN).forward(httpServletRequest, httpServletResponse);
                    return flag;
                }
            } else {
                flag = true;
            }
        }
        return flag;
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
