package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.model.bean.R;
import com.blog.model.converter.RoleForm2Role;
import com.blog.model.entity.Role;
import com.blog.mapper.RoleMapper;
import com.blog.model.entity.RolePermission;
import com.blog.model.form.RoleForm;
import com.blog.service.AdminRoleService;
import com.blog.service.RolePermissionService;
import com.blog.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private AdminRoleService adminRoleService;

    @Override
    public R deleteById(Long id) {
        int row = roleMapper.deleteById(id);
        if (row < 1) {

            return R.error("删除失败");
        }
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(RolePermission::getRoleId, id);
        rolePermissionService.remove(queryWrapper);
        R r = adminRoleService.deleteAdminRoleById(id);
        if(r.getCode() < 1){

            return r;
        }
        return R.ok("删除角色成功");
    }

    @Override
    public R update(RoleForm roleForm) {
        Role role = roleMapper.selectById(roleForm.getId());
        role = RoleForm2Role.converter(roleForm);
        int row = roleMapper.updateById(role);
        if (row < 1) {

            return R.error("修改失败");
        }
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(RolePermission::getRoleId, role.getId());
        rolePermissionService.remove(queryWrapper);

        List<Long> permissions = roleForm.getPermissions();
        for (Long permission : permissions) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permission);
            rolePermission.setRoleId(role.getId());
            rolePermissionService.save(rolePermission);
        }

        return R.ok("修改成功");
    }

    @Override
    public R save(RoleForm roleForm) {
        Role role = RoleForm2Role.converter(roleForm);
        int row = roleMapper.insert(role);
        if (row <= 0) {

            return R.error("添加失败");
        }
        List<Long> permissions = roleForm.getPermissions();
        for (Long permission : permissions) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permission);
            rolePermission.setRoleId(role.getId());
            rolePermissionService.save(rolePermission);
        }

        return R.ok("添加成功");
    }
}
