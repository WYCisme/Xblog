package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.model.entity.ArticleLabel;
import com.blog.mapper.ArticleLabelMapper;
import com.blog.model.bean.R;
import com.blog.service.ArticleLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Service
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelMapper, ArticleLabel> implements ArticleLabelService {


    @Override
    public R saveByTagsAndArticleId(List<Long> tagIds , long articleId){
        boolean flag = false;
        //标签与文章的关系
        for (Long tagId : tagIds) {
            QueryWrapper<ArticleLabel> articleLabelQueryWrapper = new QueryWrapper<>();
            articleLabelQueryWrapper.lambda().eq(ArticleLabel::getArticleId, articleId);
            articleLabelQueryWrapper.lambda().eq(ArticleLabel::getLabelId, tagId);
            ArticleLabel articleLabel = this.getOne(articleLabelQueryWrapper);
            if(articleLabel == null) {
                articleLabel = new ArticleLabel();
                articleLabel.setArticleId(articleId);
                articleLabel.setLabelId(tagId);
                flag = this.save(articleLabel);
                if (!flag) {
                    return R.error();
                }
            }else{
                articleLabel.setArticleId(articleId);
                articleLabel.setUpdateTime(LocalDateTime.now());
                flag = this.updateById(articleLabel);
                if (!flag) {
                    return R.error();
                }
            }

        }

        return R.ok("添加标签成功!");
    }
}
