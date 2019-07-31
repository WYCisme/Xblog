package com.blog.model.form;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengxin
 * @since 2019-04-23
 */
@Data
public class PermissionForm {

    private Long id;

    /** 权限 **/
    @NotEmpty(message = "权限必填")
    private String permission;

    private String description;


}
