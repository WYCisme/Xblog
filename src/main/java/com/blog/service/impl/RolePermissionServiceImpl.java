package com.blog.service.impl;

import com.blog.model.bean.R;
import com.blog.model.converter.RolePermissionFormConverter;
import com.blog.model.entity.RolePermission;
import com.blog.mapper.RolePermissionMapper;
import com.blog.model.form.RolePermissionForm;
import com.blog.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {


    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public R deleteById(Long id) {
        int row = rolePermissionMapper.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功");
        }
        return R.error("删除失败");
    }

    @Override
    public R update(RolePermissionForm rolePermissionForm) {
        RolePermission rolePermission = rolePermissionMapper.selectById(rolePermissionForm.getId());
        rolePermission = RolePermissionFormConverter.formToObj(rolePermissionForm);
        int row = rolePermissionMapper.updateById(rolePermission);
        if (row > 0) {
            return R.ok("修改成功");
        }
        return R.error("修改失败");
    }

    @Override
    public R add(RolePermissionForm rolePermissionForm) {
        RolePermission rolePermission = RolePermissionFormConverter.formToObj(rolePermissionForm);
        int row = rolePermissionMapper.insert(rolePermission);
        if (row > 0) {
            return R.ok("添加成功");
        }
        return R.error("添加失败");
    }

}
