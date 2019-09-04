package com.blog.model.form;

import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.blog.model.entity.Admin;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Article 提交对象 {@link com.blog.model.entity.Article}
 *
 * @author zx
 * @date 2018/12/18
 */
@Data
public class ArticleForm {

    private Long id;

    /**
     * 文章标题
     */
    @NotEmpty(message = "文章标题必填")
    @Length(min = 0, max = 64)
    private String title;

    /**
     * 文章标签
     */
    private String labels;

    /**
     * 频道
     */
    private String channel;

    /**
     * 用户ID
     */
    private Long adminId;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 状态(-1 删除 ,0. 待发布 ,1.正常)
     */
    private String status;


    /**
     * 文章token
     */
    private String submitToken;


    public interface ArticleGroup{}
}
