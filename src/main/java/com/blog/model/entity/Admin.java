package com.blog.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.blog.common.utils.EntityUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Admin 实体类
 *
 * @author zx
 * @date 2018/12/18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin extends Model<Admin> {

    /** 用户ID **/
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 用户名 **/
    private String username;

    /** 昵称 **/
    private String nickname;

    /** 用户名头像 **/
    private String headImg;

    /** 密码 **/
    private String password;

    /** 状态 **/
    private Integer status;

    /** 创建时间 **/
    private Date createDate;

    /** 更新时间 **/
    private Date updateTime;

    /** 提交凭证**/
    private String submitToken;

    /** 盐 **/
    private String salt;

    /** 加密 **/
    @TableField(exist=false)
    private String sign;

    public String getSign() {
        return EntityUtils.encodeSign(this.id);
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
