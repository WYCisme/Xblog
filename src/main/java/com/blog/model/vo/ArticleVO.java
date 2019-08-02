package com.blog.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.blog.common.utils.EntityUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

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
public class ArticleVO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private transient Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 用户ID
     */
    private Long adminId;

    /**
     * 用户名
     */
    private String adminName;

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
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 文章简介
     */
    private String intro;


    /**
     * 图片集
     */
    private transient String images;

    public JSONArray getImages() {
        if (StringUtils.isBlank(images)) {
            return new JSONArray();
        }
        return  JSONArray.fromObject(this.images);
    }



    /** 加密 **/
    @TableField(exist = false)
    private String sign;

    public String getSign() {
        return EntityUtils.encodeSign(this.id);
    }


    /** 排序 **/
    @TableField(exist = false)
    private String sort;


}
