package com.blog.model.converter;

import com.blog.model.entity.RolePermission;
import com.blog.model.form.RolePermissionForm;
import org.springframework.beans.BeanUtils;


/**
 * 转换类
 * 
 * @author zx
 * @date 2019/4/24
 */
public class RolePermissionFormConverter
{

    public static RolePermission formToObj(RolePermissionForm rolePermissionForm) {
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(rolePermissionForm, rolePermission);
        return rolePermission;
    }

    public static RolePermission formToObj(RolePermissionForm rolePermissionForm, RolePermission rolePermission) {
        BeanUtils.copyProperties(rolePermissionForm, rolePermission);
        return rolePermission;
    }
}
