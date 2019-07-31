package com.blog.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.controller.base.BaseController;
import com.blog.model.entity.Admin;
import com.blog.model.form.AdminForm;
import com.blog.model.bean.R;
import com.blog.service.AdminService;
import lombok.extern.slf4j.Slf4j;
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
 * 后台-用户
 *
 * @author zx
 * @date 2018/12/23
 */
@Controller
@Slf4j
@RequestMapping("/back/admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    /**
     * 用户操作首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "back/admin/listAdmin";
    }

    /**
     * 查询数据
     *
     * @param page
     * @param limit
     * @param admin
     * @return
     */
    @RequiresPermissions("admin:list")
    @RequestMapping(value = "/listAdmin")
    public @ResponseBody R listAdmin(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(
        defaultValue = "10") Integer limit, @ModelAttribute Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Admin::getUsername, admin.getUsername());
        queryWrapper.lambda().eq(Admin::getNickname, admin.getNickname());
        IPage<Admin> pages = admin.selectPage(new Page<>(page, limit), queryWrapper);
        return R.page(pages);
    }

    /**
     * 删除用户数据
     *
     * @param ids
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("admin:del")
    @RequestMapping(value = "{ids}/removeAdmin")
    public @ResponseBody R removeAdmin(@PathVariable("ids") String ids, ModelAndView modelAndView) {
        String[] idArray = ids.split(",");
        boolean flag = true;
        for (String s : idArray) {
            R result = adminService.deleteAdminById(NumberUtils.toLong(s));
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
    @RequiresPermissions("admin:edit")
    @GetMapping("{id}/toEditAdmin")
    public ModelAndView toEditAdmin(@PathVariable("id") Long id, ModelAndView modelAndView) {
        Admin admin = adminService.getById(id);
        modelAndView.addObject("admin", admin);
        modelAndView.setViewName("back/admin/toEditAdmin");
        return modelAndView;
    }

    /**
     * 更新用户数据
     *
     * @param flag
     * @param adminForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("admin:edit")
    @RequestMapping(value = "/editAdmin")
    public ModelAndView editAdmin(String flag, @ModelAttribute @Valid AdminForm adminForm, ModelAndView modelAndView,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 修改用户 ] 参数不正确 , adminForm ={} ", adminForm);
            modelAndView.setViewName("forward:/back/admin/toAddAdmin");
            modelAndView.addObject("message","参数不正确");
            return modelAndView;
        }
        R result = adminService.updateAdmin(adminForm);
        modelAndView.setViewName("forward:/back/admin/index");
        modelAndView.addObject("message", result.getMsg());
        return modelAndView;
    }

    /**
     * 进入添加
     *
     * @return
     */
    @RequiresPermissions("admin:add")
    @GetMapping("/toAddAdmin")
    public ModelAndView toAddAdmin() {
        return new ModelAndView("back/admin/toAddAdmin");
    }

    /**
     * 添加用户数据
     *
     * @param adminForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("admin:add")
    @PostMapping(value = "/addAdmin")
    public ModelAndView addAdmin(@ModelAttribute @Valid AdminForm adminForm, ModelAndView modelAndView,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 添加用户 ] 参数不正确 , adminForm ={} ", adminForm);
            modelAndView.setViewName("forward:/back/admin/toAddAdmin");
            modelAndView.addObject("message","参数不正确");
            return modelAndView;
        }
        R result = adminService.addAdmin(adminForm);
        modelAndView.setViewName("forward:/back/admin/index");
        modelAndView.addObject("message", result.getMsg());
        return modelAndView;
    }

}
