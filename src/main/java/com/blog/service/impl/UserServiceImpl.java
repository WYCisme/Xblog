package com.blog.service.impl;

import java.time.LocalDateTime;

import com.blog.mapper.UserMapper;
import com.blog.model.bean.R;
import com.blog.model.converter.UserForm2User;
import com.blog.model.entity.User;
import com.blog.model.form.UserForm;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhengxin
 * @since 2019-04-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public R deleteUserById(Long id) {
        int row = userMapper.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功");
        }
        return R.error("删除失败");
    }

    @Override
    public R updateUser(UserForm userForm) {
        User user = userMapper.selectById(userForm.getId());
        user = UserForm2User.converter(userForm);
        int row = userMapper.updateById(user);
        if (row > 0) {
            return R.ok("修改成功");
        }
        return R.error("修改失败");
    }

    @Override
    public R addUser(UserForm userForm) {
        User user = UserForm2User.converter(userForm);
        user.setCreateDate(LocalDateTime.now());
        user.setCount(0);
        int row = userMapper.insert(user);
        if (row > 0) {
            return R.ok("添加成功");
        }
        return R.error("添加失败");
    }

    @Override
    public User login(String loginname, String password) {
        return userMapper.login(loginname, password);
    }
}
