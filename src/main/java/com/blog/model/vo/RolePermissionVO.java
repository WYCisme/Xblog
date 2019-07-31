package com.blog.model.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengxin
 * @since 2019-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RolePermissionVO extends Model<RolePermissionVO> {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long permissionId;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
