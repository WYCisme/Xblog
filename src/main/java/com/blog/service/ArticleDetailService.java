package com.blog.service;

import com.blog.model.bean.R;
import com.blog.model.entity.ArticleDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-08-02
 */
public interface ArticleDetailService extends IService<ArticleDetail> {

    /**
     * 浏览量
     *
     * @param ids
     * @return
     */
    R updateViewCount(List<Long> ids);


    /**
     * 点赞数
     *
     * @param ids
     * @return
     */
    R updateUpCount(List<Long> ids);

    /**
     * 踩人数
     *
     * @param ids
     * @return
     */
    R updateDownCount(List<Long> ids);

    /**
     * 新增文章
     *
     * @param articleId
     * @return
     */
    R save(Long articleId);
}
