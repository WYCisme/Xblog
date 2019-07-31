package com.blog.model.converter;

import org.springframework.beans.BeanUtils;

import com.leave.model.entity.Role;
import com.leave.model.form.RoleForm;

/**
 * 转换类
 * 
 * @author zx
 * @date 2019/4/24
 */
public class RoleForm2Role {

    public static Role converter(RoleForm roleForm) {
        Role role = new Role();
        BeanUtils.copyProperties(roleForm, role);
        return role;
    }

    public static Role converter(RoleForm roleForm, Role role) {
        BeanUtils.copyProperties(roleForm, role);
        return role;
    }
}
