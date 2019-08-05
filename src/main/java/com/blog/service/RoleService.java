package com.blog.service;

import com.blog.model.bean.R;
import com.blog.model.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.form.RoleForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
public interface RoleService extends IService<Role> {

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
     * @param roleForm
     * @return
     */
    R update(RoleForm roleForm);

    /**
     * 添加
     *
     * @param roleForm
     * @return
     */
    R save(RoleForm roleForm);

}
