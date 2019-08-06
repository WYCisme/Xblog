package com.blog.controller.back;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.model.bean.R;
import com.blog.model.entity.Permission;
import com.blog.model.entity.PermissionType;
import com.blog.model.entity.PermissionType;
import com.blog.model.form.PermissionTypeForm;
import com.blog.service.PermissionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengxin
 * @since 2019-08-05
 */
@Controller
@Slf4j
@RequestMapping("/back/permission-type")
public class PermissionTypeController
{


    @Autowired
    protected PermissionTypeService permissionTypeService;
    /**
     * 权限类型操作首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "/back/permissiontype/permission-type-list";
    }

    /**
     * 查询数据
     *
     * @param page
     * @param limit
     * @param permissionType
     * @return
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    R list(@RequestParam(value = "permissionVOPage", defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "10") Integer limit, @ModelAttribute PermissionType permissionType) {
        QueryWrapper<PermissionType> queryWrapper = new QueryWrapper<>();
        IPage<PermissionType> pages = permissionType.selectPage(new Page<>(page, limit), queryWrapper);
        return R.page(pages);
    }

    /**
     * 删除权限类型数据
     *
     * @param ids
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "{ids}/del")
    public @ResponseBody R del(@PathVariable("ids") String ids, ModelAndView modelAndView) {
        String[] idArray = ids.split(",");
        boolean flag = true;
        for (String s : idArray) {
            R result = permissionTypeService.deleteById(NumberUtils.toLong(s));
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
     * 更新权限类型数据
     *
     * @param permissionTypeForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/edit")
    public @ResponseBody
    R editPermissionTypeType(@ModelAttribute @Valid PermissionTypeForm permissionTypeForm, ModelAndView modelAndView,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 修改权限类型 ] 参数不正确 , permissionTypeForm ={} ", permissionTypeForm);
            return R.error("参数不正确");
        }
        R result = permissionTypeService.update(permissionTypeForm);
        return R.ok(result.getMsg());
    }


    /**
     * 添加权限类型数据
     *
     * @param permissionTypeForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/save")
    public @ResponseBody R save(@ModelAttribute @Valid PermissionTypeForm permissionTypeForm, ModelAndView modelAndView,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 添加权限类型 ] 参数不正确 , permissionTypeForm ={} ", permissionTypeForm);
            return R.error("参数不正确");
        }
        R result = permissionTypeService.save(permissionTypeForm);
        return R.ok(result.getMsg());
    }
}
