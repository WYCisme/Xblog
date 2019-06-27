package com.blog.model.form;

import java.io.Serializable;
import java.util.Date;

import com.blog.model.entity.Admin;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Admin 提交对象
 * {@link Admin}
 *
 * @author zx
 * @date 2018/12/18
 */
@Data
public class AdminForm {

    /** 登录ID **/
    private Long id;

    /** 用户名 **/
    @NotEmpty(message = "用户名必填")
    private String username;

    /** 昵称 **/
    @NotEmpty(message = "昵称必填")
    private String nickname;

    /** 密码 **/
    @NotEmpty(message = "密码必填")
    private String password;

}
