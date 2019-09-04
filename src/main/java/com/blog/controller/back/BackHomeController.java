package com.blog.controller.back;

import com.blog.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: // TODO
 * <br>
 * @date: 2019/9/4 14:45
 * @author: zhengxin
 * @version: mall V1.0
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("/back/home")
public class BackHomeController extends BaseController {


    /**
     * 文章主页
     *
     * @return
     */
    @GetMapping("/article")
    public String index(Model model) {
        return "/back/article/article-list";
    }
}
