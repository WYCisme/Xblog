package com.blog.service.impl;

import com.blog.BaseUtil;
import com.blog.model.entity.Article;
import com.blog.model.entity.ArticleDetail;
import com.blog.service.ArticleDetailService;
import com.blog.service.ArticleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ArticleDetailServiceImplTest extends BaseUtil
{


    @Autowired
    ArticleService articleService;


    @Autowired
    ArticleDetailService articleDetailService;

    @Test
    public void save()
    {

        List<Article> list = articleService.list();
        list.forEach(a ->
        {
            ArticleDetail articleDetail = articleDetailService.getById(a.getId());
            if(articleDetail == null){
                articleDetailService.save(a.getId());
            }
        });
    }
}