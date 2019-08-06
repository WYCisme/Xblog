/**
 * Project Name: Xblog
 * Class Name: com.blog.model.form.PermissionTypeForm
 *
 * @date: 2019/8/6 9:16
 * <p>
 * Copyright (C) 2019,suntang.com All Right Reserved.
 */
package com.blog.model.form;

import com.blog.model.entity.PermissionType;
import lombok.Data;

/**
 * PermissionType 提交对象
 * {@link PermissionType}
 *
 * @author zx
 * @date 2018/12/18
 */
@Data
public class PermissionTypeForm
{

    private Long id;
    /**
     * 分类名
     */
    private String name;

}
