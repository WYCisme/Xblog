package com.blog.model.form;

import java.io.Serializable;
import java.util.List;

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
public class RoleForm  {

    private Long id;

    /** 角色 **/
    @NotEmpty(message = "角色必填")
    private String role;

    private String description;

    List<Long> permissions;


    public interface RoleGroup{}
}
