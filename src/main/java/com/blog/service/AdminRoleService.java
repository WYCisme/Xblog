package com.blog.service;

import com.blog.model.bean.R;
import com.blog.model.entity.AdminRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.form.AdminRoleForm;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
public interface AdminRoleService extends IService<AdminRole> {

    /**
     * 删除
     *
     * @param id
     * @return
     */
    R deleteAdminRoleById(Long id);

    /**
     * 删除根据角色ID
     *
     * @param roleId
     * @return
     */
    R deleteAdminRoleByRoleId(Long roleId);

    /**
     * 修改
     *
     * @param adminRoleForm
     * @return
     */
    R updateAdminRole(AdminRoleForm adminRoleForm);

    /**
     * 添加
     *
     * @param adminRoleForm
     * @return
     */
    R addAdminRole(AdminRoleForm adminRoleForm);

    /**
     * 创建管理员的
     * @param id
     * @param roles
     */
    R addAdminRoleByAdminId(Long id, List<Long> roles);

}
