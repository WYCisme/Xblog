package com.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.blog.common.constants.AppConstants;
import com.blog.common.utils.EntityUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题 
     */
    private String title;

    /**
     * 文章简介
     */
    private String intro;

    /**
     * 文章提交凭证
     */
    private String submitToken;

    /**
     * 盐
     */
    private String salt;

    /**
     * 用户ID
     */
    private Long adminId;

    /**
     * 频道名称[冗余]
     */
    private String channel;

    /**
     * 标签[冗余]
     */
    private String labels;
    /**
     * 文章内容
     */
    private String content;

    /**
     * 状态(-1 删除 ,0. 待发布 ,1.正常)
     */
    private Integer status;

    /**
     * 浏览量
     */
    private Long viewCount;

    /**
     * 浏览量
     */
    private Long upCount;

    /**
     * 点赞数
     */
    private Long downCount;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = AppConstants.DATE_FORMAT)
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = AppConstants.DATE_FORMAT)
    private LocalDateTime updateTime;

    /**
     * 图片集
     */
    private String images;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    /** 加密 **/
    @TableField(exist=false)
    private String sign;

    public String getSign() {
        return EntityUtils.encodeSign(this.id);
    }
}
