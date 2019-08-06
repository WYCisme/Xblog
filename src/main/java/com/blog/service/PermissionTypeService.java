package com.blog.service;

import com.blog.model.bean.R;
import com.blog.model.entity.PermissionType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.form.PermissionTypeForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-08-05
 */
public interface PermissionTypeService extends IService<PermissionType> {

    R deleteById(long l);

    R update(PermissionTypeForm permissionTypeForm);

    R save(PermissionTypeForm permissionTypeForm);
}
