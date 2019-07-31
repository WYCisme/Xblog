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
public class PermissionForm2Permission {

    public static Permission converter(PermissionForm permissionForm) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionForm, permission);
        return permission;
    }

    public static Permission converter(PermissionForm permissionForm, Permission permission) {
        BeanUtils.copyProperties(permissionForm, permission);
        return permission;
    }
}
