package com.blog.controller.base;

import com.blog.common.constants.AppConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * INDEX 控制器
 *
 * @author zx
 * @date 2018/12/23
 */
@Controller
@RequestMapping("/")
public class IndexController {


    @RequestMapping(value="/")
    public String index(){
        return "redirect:"+AppConstants.HOME;
    }

}
