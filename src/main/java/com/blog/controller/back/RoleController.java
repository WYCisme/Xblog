package com.blog.controller.back;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.utils.ValidatorUtils;
import com.blog.model.bean.R;
import com.blog.model.entity.Permission;
import com.blog.model.entity.Role;
import com.blog.model.entity.RolePermission;
import com.blog.model.form.RoleForm;
import com.blog.model.form.RoleForm.RoleGroup;
import com.blog.model.vo.PermissionVO;
import com.blog.service.PermissionService;
import com.blog.service.RolePermissionService;
import com.blog.service.RoleService;
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
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@RestController
@Slf4j
@RequestMapping("/back/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 查询数据
     *
     * @param page
     * @param limit
     * @param role
     * @return
     */
    @RequiresPermissions("role:list")
    @RequestMapping(value = "/list")
    public @ResponseBody R list(@RequestParam(value = "permissionVOPage", defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "10") Integer limit, @ModelAttribute Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        IPage<Role> pages = role.selectPage(new Page<>(page, limit), queryWrapper);
        return R.page(pages);
    }

    /**
     * 删除角色数据
     *
     * @param ids
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("role:del")
    @DeleteMapping(value = "/{ids}")
    public R del(@PathVariable("ids") String ids) {
        String[] idArray = ids.split(",");
        boolean flag = true;
        for (String s : idArray) {
            R result = roleService.deleteById(NumberUtils.toLong(s));
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
     * @param roleForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("role:edit")
    @RequestMapping(value = "/edit")
    public @ResponseBody R editRole(@ModelAttribute RoleForm roleForm){
        ValidatorUtils.validateEntity(roleForm, RoleGroup.class);
        R result = roleService.update(roleForm);
        return R.ok(result.getMsg());
    }



    /**
     * 添加角色数据
     *
     * @param roleForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("role:save")
    @PostMapping(value = "/")
    public @ResponseBody R save(@ModelAttribute @Valid RoleForm roleForm) {
        ValidatorUtils.validateEntity(roleForm, RoleGroup.class);
        R result = roleService.save(roleForm);
        return R.ok(result.getMsg());
    }

}
