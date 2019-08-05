/**
 * Project Name: Xblog Class Name: com.blog.controller.back.UserController
 *
 * @date: 2019/7/29 13:47
 *        <p>
 *        Copyright (C) 2019,suntang.com All Right Reserved.
 */
package com.blog.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.model.bean.R;
import com.blog.model.entity.User;
import com.blog.model.form.UserForm;
import com.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * 用户类
 *
 * @author zx
 * @date 2019/7/31
 */
@Controller
@Slf4j
@RequestMapping("/back/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/welcome")
    public ModelAndView welcome() {

        return new ModelAndView("back/user/welcome");
    }

    /**
     * 学生操作首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "back/user/listUser";
    }

    /**
     * 查询数据
     *
     * @param page
     * @param limit
     * @param user
     * @return
     */
    @RequiresPermissions("user:list")
    @RequestMapping(value = "/listUser")
    public @ResponseBody R listUser(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(
        defaultValue = "10") Integer limit, @ModelAttribute User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(user.getUsername())) {
            queryWrapper.lambda().like(User::getUsername, "%" + user.getUsername() + "%");
        }
        IPage<User> pages = user.selectPage(new Page<>(page, limit), queryWrapper);
        return R.page(pages);
    }

    /**
     * 删除学生数据
     *
     * @param ids
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("user:del")
    @RequestMapping(value = "{ids}/removeUser")
    public @ResponseBody R removeUser(@PathVariable("ids") String ids, ModelAndView modelAndView) {
        String[] idArray = ids.split(",");
        boolean flag = true;
        for (String s : idArray) {
            R result = userService.deleteUserById(NumberUtils.toLong(s));
            if (result.getCode() <= 0) {
                flag = false;
            }
        }
        if (flag) {
            return R.ok("删除成功");
        }
        return R.error("删除失败!");
    }

    /**
     * 进入更新
     *
     * @return
     */
    @RequiresPermissions("user:edit")
    @GetMapping("{id}/toEditUser")
    public ModelAndView toEditUser(@PathVariable("id") Long id, ModelAndView modelAndView) {
        User user = userService.getById(id);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("back/user/toEditUser");
        return modelAndView;
    }

    /**
     * 更新学生数据
     *
     * @param userForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("user:edit")
    @RequestMapping(value = "/editUser")
    public ModelAndView editUser(@ModelAttribute @Valid UserForm userForm, ModelAndView modelAndView,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 修改学生 ] 参数不正确 , userForm ={} ", userForm);
            modelAndView.setViewName("forward:/back/user/toAddUser");
            modelAndView.addObject("message", "参数不正确");
            return modelAndView;
        }
        R result = userService.updateUser(userForm);
        modelAndView.setViewName("forward:/back/user/index");
        modelAndView.addObject("message", result.getMsg());
        return modelAndView;
    }

    /**
     * 进入添加
     *
     * @return
     */
    @RequiresPermissions("user:save")
    @GetMapping("/toAddUser")
    public ModelAndView toAddUser() {
        return new ModelAndView("back/user/toAddUser");
    }

    /**
     * 添加学生数据
     *
     * @param userForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("user:save")
    @PostMapping(value = "/addUser")
    public ModelAndView addUser(@ModelAttribute @Valid UserForm userForm, ModelAndView modelAndView,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 添加学生 ] 参数不正确 , userForm ={} ", userForm);
            modelAndView.setViewName("forward:/back/user/toAddUser");
            modelAndView.addObject("message", "参数不正确");
            return modelAndView;
        }
        R result = userService.addUser(userForm);
        modelAndView.setViewName("forward:/back/user/index");
        modelAndView.addObject("message", result.getMsg());
        return modelAndView;
    }

}
