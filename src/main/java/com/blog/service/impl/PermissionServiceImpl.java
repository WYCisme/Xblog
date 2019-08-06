package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.model.bean.R;
import com.blog.model.converter.PermissionConverter;
import com.blog.model.entity.Permission;
import com.blog.mapper.PermissionMapper;
import com.blog.model.form.PermissionForm;
import com.blog.model.vo.PermissionVO;
import com.blog.service.PermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public R deleteById(Long id) {
        int row = permissionMapper.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功");
        }
        return R.error("删除失败");
    }

    @Override
    public R update(PermissionForm permissionForm) {
        Permission permission = permissionMapper.selectById(permissionForm.getId());
        permission = PermissionConverter.formToObj(permissionForm);
        int row = permissionMapper.updateById(permission);
        if (row > 0) {
            return R.ok("修改成功");
        }
        return R.error("修改失败");
    }

    @Override
    public R save(PermissionForm permissionForm) {
        Permission permission = PermissionConverter.formToObj(permissionForm);
        int row = permissionMapper.insert(permission);
        if (row > 0) {
            return R.ok("添加成功");
        }
        return R.error("添加失败");
    }

    @Override
    public Page<PermissionVO> permissionVOPage(Page<PermissionVO> page, Wrapper<PermissionVO> queryWrapper) {
        return page.setRecords(this.permissionMapper.page(page , queryWrapper));
    }


    @Override
    public List<PermissionVO> permissionVOList(Wrapper<PermissionVO> queryWrapper){
        return this.permissionMapper.list(queryWrapper);
    }



}
