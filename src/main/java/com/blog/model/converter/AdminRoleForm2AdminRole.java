package com.blog.model.converter;

import com.blog.model.entity.AdminRole;
import com.blog.model.form.AdminRoleForm;
import org.springframework.beans.BeanUtils;

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
