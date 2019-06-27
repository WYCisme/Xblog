package com.blog.service;

import com.blog.model.entity.ArticleLabel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.vo.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
public interface ArticleLabelService extends IService<ArticleLabel> {

    public R saveByTagsAndArticleId(List<Long> tagIds , long articleId );
}
