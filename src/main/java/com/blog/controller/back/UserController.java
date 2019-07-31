/**
 * Project Name: Xblog
 * Class Name: com.blog.controller.back.UserController
 *
 * @date: 2019/7/29 13:47
 * <p>
 * Copyright (C) 2019,suntang.com All Right Reserved.
 */
package com.blog.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description: // TODO
 * <br>
 * @date: 2019/7/29 13:47
 * @author: z00000043/zhengxin
 * @version: SmartCityManager V1.0
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("/back/user")
public class UserController
{

    @RequestMapping("/welcome")
    public ModelAndView welcome(){

        return new ModelAndView("back/user/welcome");
    }

}
