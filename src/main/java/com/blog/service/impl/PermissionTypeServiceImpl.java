package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.model.bean.R;
import com.blog.model.converter.PermissionTypeConverter;
import com.blog.model.entity.Permission;
import com.blog.model.entity.PermissionType;
import com.blog.mapper.PermissionTypeMapper;
import com.blog.model.form.PermissionTypeForm;
import com.blog.service.PermissionService;
import com.blog.service.PermissionTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhengxin
 * @since 2019-08-05
 */
@Service
public class PermissionTypeServiceImpl extends ServiceImpl<PermissionTypeMapper, PermissionType>
    implements PermissionTypeService
{

    @Autowired
    protected PermissionService permissionService;

    @Override
    public R deleteById(long id)
    {
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.lambda().eq(Permission::getPermissionTypeId, id);
        List<Permission> permissionList = permissionService.list(permissionQueryWrapper);
        if(permissionList.isEmpty()){
            boolean flag = super.removeById(id);
            if(flag){
                return R.ok("删除成功!");
            }
        }
        return R.error("删除失败,存在权限使用此分类!");
    }

    @Override
    public R update(PermissionTypeForm permissionTypeForm)
    {
        PermissionType permissionType = super.getById(permissionTypeForm.getId());
        if(permissionType == null){

            return R.error("修改失败!");
        }
        permissionType.setName(permissionTypeForm.getName());
        boolean flag = super.updateById(permissionType);
        if(flag){

            return R.ok("修改成功!");
        }
        return R.error("修改失败!");
    }

    @Override
    public R save(PermissionTypeForm permissionTypeForm)
    {
        PermissionType permissionType = PermissionTypeConverter.permissionTypeForm2PermissionType(permissionTypeForm);
        permissionType.setCreateDate(LocalDateTime.now());
        boolean flag = super.save(permissionType);
        if(flag){

            return R.ok("保存成功!");
        }
        return R.error("保存失败");
    }
}
