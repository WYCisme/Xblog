package com.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.model.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.form.ArticleForm;
import com.blog.model.dto.ArticleDTO;
import com.blog.model.vo.ArticleVO;
import com.blog.model.bean.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
public interface ArticleService extends IService<Article> {


    /**
     * 修改
     * @param articleForm
     * @return
     */
    R updateById(ArticleForm articleForm);


    /**
     * 保存
     * @param articleForm
     * @return
     */
    R save(ArticleForm articleForm);


    /**
     * 保存
     * @param articleVO
     * @return
     */
    R save(ArticleDTO articleVO);


    /**
     * 修改
     * @param articleVO
     * @return
     */
    R updateBySign(ArticleDTO articleVO);


    /**
     * 前台翻页
     * @param page
     * @param queryWrapper
     * @return
     */
    Page<ArticleVO> pageFrontArticleVO(Page<ArticleVO> page, ArticleVO articleVO);

    /**
     * ID查找
     *
     * @param id
     * @return
     */
    ArticleVO findById(Long id);


    /**
     * 浏览量
     *
     * @param ids
     * @return
     */
    R updateArticleViewCount(List<Long> ids);


    /**
     * 点赞数
     *
     * @param ids
     * @return
     */
    R updateArticleUpCount(List<Long> ids);

    /**
     * 踩人数
     *
     * @param ids
     * @return
     */
    R updateArticleDownCount(List<Long> ids);
}
