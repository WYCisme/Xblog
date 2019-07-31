package com.blog.service;

import com.blog.model.bean.R;
import com.blog.model.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.form.PermissionForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
public interface PermissionService extends IService<Permission> {

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
     * @param permissionForm
     * @return
     */
    R update(PermissionForm permissionForm);

    /**
     * 添加
     *
     * @param permissionForm
     * @return
     */
    R add(PermissionForm permissionForm);

}
