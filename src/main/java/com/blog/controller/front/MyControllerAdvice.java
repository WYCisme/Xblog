package com.blog.controller.front;

import com.blog.common.utils.WebUtils;
import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 通知,系统管理员
 */
@ControllerAdvice
public class MyControllerAdvice {

    @Data
    public class CurrUser {
        private String name = "郑新";
        private String username = "Avalon";
        private String email = "694948443@qq.com";
        private String wechat = "slucky694948443";
        private String phone = "13008891817";
        private String qq = "694948443";
        private String image = WebUtils.getBaseUrl() + "/static-front/images/userIcon.jpg";
        private String backImage = WebUtils.getBaseUrl() + "/static-front/images/backImage.jpg";
        private String description = "书中自有颜如玉,书中自有黄金屋";
        private String job = "Java工程师";

    }

    @ModelAttribute(name = "currUser")
    public CurrUser currUser() {
        return new CurrUser();
    }

}