package com.blog.controller.base;

import com.blog.common.constants.SystemConstants;
import com.blog.common.utils.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 基类控制器
 *
 * @author zx
 * @date 2019/1/28
 */
@Slf4j
@Controller
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    @Autowired
    protected SystemConstants configConstants;

    @Autowired
    protected CacheUtil cacheUtil;


    /**
     * 添加Flash消息
     *
     * @param redirectAttributes
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();

        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }

        redirectAttributes.addFlashAttribute("message", sb.toString());

        log.debug(" message : " + sb.toString());
    }

    /**
     * 添加项目根路径
     *
     * @param request
     */
    private void putBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
        request.setAttribute("basePath", path);
        request.setAttribute("base", tempContextUrl);
    }

}
