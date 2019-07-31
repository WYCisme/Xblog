package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.AdminMapper;
import com.blog.model.converter.AdminConverter;
import com.blog.model.entity.Admin;
import com.blog.model.form.AdminForm;
import com.blog.model.bean.R;
import com.blog.service.AdminService;
import com.blog.common.utils.EncryptUtil;
import com.blog.common.constants.SystemConstants;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户Service 实现类
 *
 * @author zx
 * @date 2018/12/23
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String loginname, String password) {
        return adminMapper.selectByLoginnameAndPassword(loginname, password);
    }

    @Override
    public R deleteAdminById(Long id) {
        int row = adminMapper.deleteById(id);
        if(row > 0){
           return  R.ok("删除成功");
        }
        return R.error("删除失败");
    }

    @Override
    public R updateAdmin(AdminForm adminForm) {
        Admin admin = adminMapper.selectById(adminForm.getId());
        admin = AdminConverter.form2admin(adminForm);
        int row = adminMapper.update(admin,null);
        if(row > 0){
            return  R.ok("修改成功");
        }
        return R.error("修改失败");
    }

    @Override
    public R addAdmin(AdminForm adminForm) {
        Admin admin = AdminConverter.form2admin(adminForm);
        admin.setStatus(1);
        admin.setCreateDate(new Date());
        admin.setSalt(RandomStringUtils.randomAlphanumeric(16));
        admin.setSubmitToken(EncryptUtil.getInstance().encodeAes(admin.getSalt() , SystemConstants.DESKEY));
        int row = adminMapper.insert(admin);
        if(row > 0){
            return  R.ok("添加成功");
        }
        return R.error("添加失败");
    }

    @Override
    public R updateHeadImg(Admin admin) {
        int row = adminMapper.update(admin, null);
        if(row > 0){
            return R.ok("修改成功");
        }
        return R.error("修改失败");
    }

    @Override
    public List<String> findPermissions(Long userId) {
        return adminMapper.findAdminPermission(userId);
    }

    @Override
    public List<String> findRole(Long userId) {
        return adminMapper.findAdminRole(userId);
    }
}
