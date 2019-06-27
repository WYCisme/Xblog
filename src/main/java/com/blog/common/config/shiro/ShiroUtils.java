package com.blog.common.config.shiro;

import com.blog.exception.RestException;
import com.blog.model.entity.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


/**
 * Shiro工具类
 *
 * @author Avalon
 * @date 2019/3/26
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Admin getUserEntity() {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        if (admin == null) {
            throw new RestException("您当前没有权限", 401);
        }
        return admin;
    }

    public static Long getUserId() {
        return getUserEntity().getId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

//    public static String getKaptcha(String key) {
//        Object kaptcha = getSessionAttribute(key);
//        if (kaptcha == null) {
//            throw new RestException("验证码已失效");
//        }
//        getSession().removeAttribute(key);
//        return kaptcha.toString();
//    }

}
