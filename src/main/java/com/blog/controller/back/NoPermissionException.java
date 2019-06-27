package com.blog.controller.back;

import com.blog.common.constants.AppConstants;
import com.blog.common.utils.WebUtils;
import com.blog.controller.base.BaseController;
import com.blog.model.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * 描述：无权限页面
 *
 * @author Avalon
 * @date 2019/3/29
 */
@Slf4j
@ControllerAdvice
public class NoPermissionException extends BaseController {

    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        return "redirect:/back/error";
    }

    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
       return "redirect:"+AppConstants.LOGIN;
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception ex){
        log.error("异常",ex);
        return "redirect:/error";
    }
}
