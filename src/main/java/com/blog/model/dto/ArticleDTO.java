package com.blog.model.dto;


import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.blog.common.utils.EntityUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import lombok.Data;

/**
 * 文章API
 *
 * @author avalon
 * @date 2019/4/27
 */
@Data
public class ArticleDTO {


    /**
     * 文章提交凭证
     */
    private String submitToken;

  /*
     * 文章标题
     */
    private String title;

    /**
     * 文章频道
     */
    private String channel;

    /**
     * 文章标签
     */
    private String labels;

    /**
     * 文章主体
     */
    private String content;

    /**
     * 文章管理员
     */
    private transient Long adminId;
}
