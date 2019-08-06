package com.blog.model.converter;

import com.blog.model.entity.Admin;
import com.blog.model.form.AdminForm;
import org.springframework.beans.BeanUtils;

/**
 * 用户类转换
 *
 * @author Avalon
 * @date 2019/1/30
 */
public class AdminConverter {

    public static Admin formToObj(AdminForm userForm ){
        Admin admin = new Admin();
        BeanUtils.copyProperties(userForm,admin);
        return admin;
    }

    public static Admin formToObj(AdminForm userForm, Admin admin ){
        BeanUtils.copyProperties(userForm,admin);
        return admin;
    }
}
