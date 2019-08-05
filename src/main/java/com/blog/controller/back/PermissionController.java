package com.blog.controller.back;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Controller
@Slf4j
@RequestMapping("/back/permission")
public class PermissionController {



    @RequestMapping("/index")
    public String index(){
        return "/back/permission/permission-list";
    }




}
