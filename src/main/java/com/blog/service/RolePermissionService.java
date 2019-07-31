package com.blog.service;

import com.blog.model.bean.R;
import com.blog.model.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.form.RolePermissionForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 删除
     *
     * @param id
     * @return
     */
    R deleteById(Long id);

    /**
     * 修改
     *
     * @param rolePermissionForm
     * @return
     */
    R update(RolePermissionForm rolePermissionForm);

    /**
     * 添加
     *
     * @param rolePermissionForm
     * @return
     */
    R add(RolePermissionForm rolePermissionForm);

}
