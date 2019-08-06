package com.blog.model.converter;

import com.blog.model.entity.Permission;
import com.blog.model.form.PermissionForm;
import org.springframework.beans.BeanUtils;


/**
 * 转换类
 * 
 * @author zx
 * @date 2019/4/24
 */
public class PermissionConverter
{

    public static Permission formToObj(PermissionForm permissionForm) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionForm, permission);
        return permission;
    }

    public static Permission formToObj(PermissionForm permissionForm, Permission permission) {
        BeanUtils.copyProperties(permissionForm, permission);
        return permission;
    }
}
