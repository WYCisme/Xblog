package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.model.bean.R;
import com.blog.model.converter.AdminRoleConverter;
import com.blog.model.entity.AdminRole;
import com.blog.mapper.AdminRoleMapper;
import com.blog.model.form.AdminRoleForm;
import com.blog.service.AdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public R deleteAdminRoleById(Long id) {
        int row = adminRoleMapper.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功");
        }
        return R.error("删除失败");
    }

    @Override
    public R deleteAdminRoleByRoleId(Long roleId) {
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(AdminRole::getRoleId , roleId);
        int row = adminRoleMapper.delete(queryWrapper);
        if (row > 0) {
            return R.ok("删除成功");
        }
        return R.error("删除失败");
    }

    @Override
    public R updateAdminRole(AdminRoleForm adminRoleForm) {
        AdminRole adminRole = adminRoleMapper.selectById(adminRoleForm.getId());
        adminRole = AdminRoleConverter.formToObj(adminRoleForm);
        int row = adminRoleMapper.updateById(adminRole);
        if (row > 0) {
            return R.ok("修改成功");
        }
        return R.error("修改失败");
    }

    @Override
    public R addAdminRole(AdminRoleForm adminRoleForm) {
        AdminRole adminRole = AdminRoleConverter.formToObj(adminRoleForm);
        int row = adminRoleMapper.insert(adminRole);
        if (row > 0) {
            return R.ok("添加成功");
        }
        return R.error("添加失败");
    }


    @Override
    public R addAdminRoleByAdminId(Long id, List<Long> roles) {
        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper();
        adminRoleQueryWrapper.lambda().eq(AdminRole::getAdminId , id);
        boolean flag = this.remove(adminRoleQueryWrapper);
        if(!flag){

            return R.error("添加管理员角色失败!");
        }
        if(roles == null){

            return R.ok("为空不添加!");
        }
        for (Long role : roles) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(id);
            adminRole.setRoleId(role);
            flag = this.save(adminRole);
            if(!flag){

                return R.error("添加管理员角色失败!");
            }
        }

        return R.ok("添加管理员角色成功!");
    }
}
