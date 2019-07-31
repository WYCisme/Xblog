package com.blog.model.form;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengxin
 * @since 2019-04-23
 */
@Data
public class LeavesForm {

    private Long id;

    /**
     * 文章标题 
     */
    @NotEmpty(message = "请甲标题不正确!")
    private String title;

    /**
     * 状态:0 申请中 , -1 申请不通过 , 2申请通过 
     */
    private Integer status;

    /**
     * 请假理由
     */
    private String description;

    /**
     * 老师备注
     */
    private String context;

    /**
     * 用户ID
     */
    @Min(value=0, message = "学生必填")
    private Long userId;

    /**
     * 老师ID
     */
    private Long adminId;

}
