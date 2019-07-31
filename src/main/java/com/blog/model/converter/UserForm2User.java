package com.blog.model.converter;

import com.blog.model.entity.User;
import com.blog.model.form.UserForm;
import org.springframework.beans.BeanUtils;

/**
 * 转换类
 * 
 * @author zx
 * @date 2019/4/24
 */
public class UserForm2User {

    public static User converter(UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        return user;
    }

    public static User converter(UserForm userForm, User user) {
        BeanUtils.copyProperties(userForm, user);
        return user;
    }
}
