package com.blog.controller.back;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.model.bean.R;
import com.blog.model.entity.Permission;
import com.blog.model.form.PermissionForm;
import com.blog.model.vo.PermissionVO;
import com.blog.service.PermissionService;
import com.blog.service.PermissionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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


    @Autowired
    protected PermissionService permissionService;

    @Autowired
    protected PermissionTypeService permissionTypeService;
    
    /**
     * 权限主页
     * 
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("types", permissionTypeService.list());
        return "/back/permission/permission-list";
    }

    /**
     * 查询数据
     *
     * @param page
     * @param limit
     * @param permission
     * @return
     */
    //    @RequiresPermissions("permission:list")
    @RequestMapping(value = "/list")
    public @ResponseBody
    R list(@RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "10") Integer limit, @ModelAttribute Permission permission) {
        QueryWrapper<PermissionVO> queryWrapper = new QueryWrapper<>();
        IPage<PermissionVO> pages = permissionService.permissionVOPage(new Page<>(page, limit), queryWrapper);

        return R.page(pages);
    }

    /**
     * 删除角色数据
     *
     * @param ids
     * @param modelAndView
     * @return
     */
//    @RequiresPermissions("permission:del")
    @RequestMapping(value = "{ids}/del")
    public @ResponseBody R del(@PathVariable("ids") String ids, ModelAndView modelAndView) {
        String[] idArray = ids.split(",");
        boolean flag = true;
        for (String s : idArray) {
            R result = permissionService.deleteById(NumberUtils.toLong(s));
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
     * 更新角色数据
     *
     * @param permissionForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    //    @RequiresPermissions("permission:edit")
    @RequestMapping(value = "/edit")
    public @ResponseBody
    R editPermission(@ModelAttribute @Valid PermissionForm permissionForm, ModelAndView modelAndView,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 修改角色 ] 参数不正确 , permissionForm ={} ", permissionForm);
            return R.error("参数不正确");
        }
        R result = permissionService.update(permissionForm);
        return R.ok(result.getMsg());
    }



    /**
     * 添加角色数据
     *
     * @param permissionForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    //    @RequiresPermissions("permission:save")
    @PostMapping(value = "/save")
    public @ResponseBody R save(@ModelAttribute @Valid PermissionForm permissionForm, ModelAndView modelAndView,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 添加角色 ] 参数不正确 , permissionForm ={} ", permissionForm);
            return R.error("参数不正确");
        }
        R result = permissionService.save(permissionForm);
        return R.ok(result.getMsg());
    }


}
