package com.blog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.model.bean.R;
import com.blog.model.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.form.PermissionForm;
import com.blog.model.vo.PermissionVO;

import java.util.List;

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
    R save(PermissionForm permissionForm);

    /**
     * 自定义翻页
     *
     * @param page
     * @return
     */
    Page<PermissionVO> permissionVOPage(Page<PermissionVO> page, Wrapper<PermissionVO> queryWrapper);

    List<PermissionVO> permissionVOList(Wrapper<PermissionVO> queryWrapper);
}
