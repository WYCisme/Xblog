package com.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdminRole extends Model<AdminRole> {

    private static final long serialVersionUID = 1L;

    private Long adminId;

    private Long roleId;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
