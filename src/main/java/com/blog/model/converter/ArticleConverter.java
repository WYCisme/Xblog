package com.blog.model.converter;

import com.blog.model.entity.Article;
import com.blog.model.form.ArticleForm;
import com.blog.model.dto.ArticleDTO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;

/**
 * 文章类转换
 *
 * @author Avalon
 * @date 2019/1/30
 */
public class ArticleConverter {

    public static Article form2aritcle(ArticleForm articleForm) {
        Article article = new Article();
        BeanUtils.copyProperties(articleForm, article);
        return article;
    }

    public static Article form2aritcle(ArticleForm articleForm, Article article) {
        BeanUtils.copyProperties(articleForm, article);
        article.setStatus(NumberUtils.toInt(articleForm.getStatus()));
        return article;
    }

    public static ArticleForm dto2form(ArticleDTO articleDTO) {
        ArticleForm articleForm = new ArticleForm();
        BeanUtils.copyProperties(articleDTO, articleForm);
        return articleForm;
    }

    public static Article dto2article(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        return article;
    }

    public static Article dto2article(ArticleDTO articleDTO, Article article) {
        BeanUtils.copyProperties(articleDTO, article);
        return article;
    }

    public static ArticleDTO article2dto(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article,articleDTO);
        return articleDTO;
    }

}
