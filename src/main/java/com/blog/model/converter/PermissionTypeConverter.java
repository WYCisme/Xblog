/**
 * Project Name: Xblog
 * Class Name: com.blog.model.formToObj.PermissionTypeConverter
 *
 * @date: 2019/8/6 9:49
 * <p>
 * Copyright (C) 2019,suntang.com All Right Reserved.
 */
package com.blog.model.converter;

import com.blog.model.entity.PermissionType;
import com.blog.model.form.PermissionTypeForm;
import org.springframework.beans.BeanUtils;

/**
 * @description: // TODO
 * <br>
 * @date: 2019/8/6 9:49
 * @author: z00000043/zhengxin
 * @version: SmartCityManager V1.0
 * @since: JDK 1.8
 */
public class PermissionTypeConverter
{

    public static PermissionType permissionTypeForm2PermissionType(PermissionTypeForm permissionTypeForm){
        PermissionType permissionType = new PermissionType();
        BeanUtils.copyProperties(permissionTypeForm,permissionType );
        return permissionType;
    }
}
