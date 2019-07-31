package com.blog.model.form;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengxin
 * @since 2019-04-23
 */
@Data
public class AdminRoleForm {

    @Min(value = 0 , message = "管理员必填")
    private Long adminId;

    @Min(value = 0 , message = "角色必填")
    private Long roleId;

    private Long id;

}
