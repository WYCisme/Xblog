package com.blog.controller.back;

import com.blog.common.constants.CacheKey;
import com.blog.common.constants.SystemConstants;
import com.blog.common.utils.CacheUtil;
import com.blog.controller.base.BaseController;
import com.blog.model.bean.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 *
 * @author Avalon
 * @date 2019/3/26
 */
@Controller
@Slf4j
@RequestMapping("/back")
public class LoginController  extends BaseController {


    @Resource
    private CacheUtil cacheUtil;

    /**
     * 前往用户登录
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(Model model) {
        return "back/loginForm";
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param httpSession
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/logining")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password,
                              @RequestParam("code") String code, HttpSession httpSession, ModelAndView modelAndView) {
        // 检查值
        R result = checkLogin(code);
        if (result.getCode() < 1) {
            modelAndView.addObject("message", result.getMsg());
            modelAndView.setViewName("forward:/back/login");
            return modelAndView;
        }
        // 1、获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();
        // 2、判断当前用户是否登录
        if (currentUser.isAuthenticated() == false) {

        }
        // 3、将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 4、认证
        try {
            currentUser.login(token);
            Session session = currentUser.getSession();
            session.setAttribute("userName", username);
            modelAndView.setViewName("forward:/back/admin/index");
            return modelAndView;
        } catch (UnknownAccountException e) {
            modelAndView.addObject("message", "账号不存在");
        } catch (IncorrectCredentialsException e) {
            modelAndView.addObject("message", "密码不正确");
        } catch (AuthenticationException e) {
            modelAndView.addObject("message", "用户验证失败");
        }
        modelAndView.setViewName("forward:/back/login");
        return modelAndView;
    }

    /**
     * 退出登录
     *
     * @param httpSession
     * @param modelAndView
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession httpSession, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/back/login");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return modelAndView;
    }



    /**
     * 检查登录
     *
     * @param code
     * @return
     */
    public R checkLogin(String code) {
        if (!SystemConstants.ISLOCAL) {
            Object cacheCode = cacheUtil.get(CacheUtil.FIVE_MINUTES, CacheKey.CACHE_IMG_CODE + session.getId());
            if (cacheCode == null) {

                return R.error("验证码已过期，请重新获取");
            }
            if (!code.equals(cacheCode)) {

                return R.error("验证码错误");
            }
        }
        return R.ok("检查成功");
    }
}
