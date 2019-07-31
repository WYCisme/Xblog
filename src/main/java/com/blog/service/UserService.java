package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.bean.R;
import com.blog.model.entity.User;
import com.blog.model.form.UserForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-04-23
 */
public interface UserService extends IService<User> {

    /**
     * 删除
     *
     * @param id
     * @return
     */
    R deleteUserById(Long id);

    /**
     * 修改
     *
     * @param userForm
     * @return
     */
    R updateUser(UserForm userForm);

    /**
     * 添加
     *
     * @param userForm
     * @return
     */
    R addUser(UserForm userForm);

    /**
     * 登录
     *
     * @param loginname
     * @param password
     * @return
     */
    User login(String loginname, String password);

}
