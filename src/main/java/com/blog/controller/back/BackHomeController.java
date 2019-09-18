package com.blog.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.controller.base.BaseController;
import com.blog.model.entity.Role;
import com.blog.model.entity.RolePermission;
import com.blog.model.vo.PermissionVO;
import com.blog.service.PermissionService;
import com.blog.service.RolePermissionService;
import com.blog.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public String article(Model model) {
        return "/back/article/article-list";
    }

    /**
     * 定时任务
     *
     * @return
     */
    @GetMapping("/schedule")
    public String schedule(Model model) {
        return "/back/schedule/schedule-list";
    }


    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleService roleService;

    @Autowired
    RolePermissionService rolePermissionService;

    /**
     * 权限
     *
     * @return
     */
    @GetMapping("/role")
    @RequiresPermissions("role:list")
    public String role(Model model) {
        return "/back/role/role-list";
    }

    /**
     * 进入 权限更新
     *
     * @return
     */
    @RequiresPermissions("role:edit")
    @GetMapping("/role/{id}/edit")
    public ModelAndView toEdit(@PathVariable("id") Long id, ModelAndView modelAndView) {
        //权限
        List<PermissionVO> permissions = permissionService.permissionVOList(new QueryWrapper<>());
        Map<Long, List<PermissionVO>> permissionMap = permissions.stream().collect(Collectors.groupingBy(PermissionVO::getPermissionTypeId));
        modelAndView.addObject("permissionMap", permissionMap);

        Role role = roleService.getById(id);
        modelAndView.addObject("role", role);

        QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
        rolePermissionQueryWrapper.lambda().eq(RolePermission::getRoleId, id);
        List<Long> myPermissions = rolePermissionService.list(rolePermissionQueryWrapper).stream().map(r -> r.getPermissionId()).collect(Collectors
                .toList());
        modelAndView.addObject("myPermissions", myPermissions);
        modelAndView.setViewName("/back/role/role-edit");
        return modelAndView;
    }

    /**
     * 进入添加
     *
     * @return
     */
    @RequiresPermissions("role:save")
    @GetMapping("/role/save")
    public ModelAndView save(ModelAndView modelAndView) {
        //权限
        List<PermissionVO> permissions = permissionService.permissionVOList(new QueryWrapper<>());
        Map<Long, List<PermissionVO>> permissionMap = permissions.stream().collect(Collectors.groupingBy(PermissionVO::getPermissionTypeId));
        modelAndView.addObject("permissionMap", permissionMap);
        modelAndView.setViewName("back/role/role-save");
        return modelAndView;
    }

}
