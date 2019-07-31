package com.blog.model.converter;

import org.springframework.beans.BeanUtils;

import com.leave.model.entity.Admin;
import com.leave.model.form.AdminForm;

/**
 * 用户类转换
 *
 * @author Avalon
 * @date 2019/1/30
 */
public class AdminForm2Admin {

    public static Admin converter(AdminForm userForm ){
        Admin admin = new Admin();
        BeanUtils.copyProperties(userForm,admin);
        return admin;
    }

    public static Admin converter(AdminForm userForm, Admin admin ){
        BeanUtils.copyProperties(userForm,admin);
        return admin;
    }
}
