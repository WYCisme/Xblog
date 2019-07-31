package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.entity.Admin;
import com.blog.model.form.AdminForm;
import com.blog.model.bean.R;
import com.blog.service.impl.AdminServiceImpl;

import java.util.List;

/**
 * {@link AdminServiceImpl}
 * 用户Servcie
 *
 * @author zx
 * @date 2018/12/23
 */
public interface AdminService extends IService<Admin> {

    /**
     * 登录
     *
     * @param loginname
     * @param password
     * @return
     */
    Admin login(String loginname, String password);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    R deleteAdminById(Long id);

    /**
     * 修改
     *
     * @param adminForm
     * @return
     */
    R updateAdmin(AdminForm adminForm);

    /**
     * 添加
     *
     * @param adminForm
     * @return
     */
    R addAdmin(AdminForm adminForm);

    /**
     * 更新用户头像
     *
     * @param admin
     * @return
     */
    R updateHeadImg(Admin admin);


    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    List<String> findPermissions(Long userId);

    /**
     * 获取用户角色
     *
     * @param userId
     * @return
     */
    List<String> findRole(Long userId);

}
