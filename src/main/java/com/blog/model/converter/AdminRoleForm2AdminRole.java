package com.blog.model.converter;

import org.springframework.beans.BeanUtils;

import com.leave.model.entity.AdminRole;
import com.leave.model.form.AdminRoleForm;

/**
 * 转换类
 * 
 * @author zx
 * @date 2019/4/24
 */
public class AdminRoleForm2AdminRole {

    public static AdminRole converter(AdminRoleForm adminRoleForm) {
        AdminRole adminRole = new AdminRole();
        BeanUtils.copyProperties(adminRoleForm, adminRole);
        return adminRole;
    }

    public static AdminRole converter(AdminRoleForm adminRoleForm, AdminRole adminRole) {
        BeanUtils.copyProperties(adminRoleForm, adminRole);
        return adminRole;
    }
}
