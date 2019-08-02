package com.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.BaseUtil;
import com.blog.mapper.ArticleMapper;
import com.blog.model.vo.ArticleVO;
import com.blog.model.bean.R;
import com.blog.service.ArticleService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author zx
 * @date 2019/5/4
 */
public class ArticleServiceImplTest extends BaseUtil {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void pageFrontArticleVO() {
        IPage<ArticleVO> pages = articleService.pageFrontArticleVO(new Page<>(1,20), null);
        System.out.println(pages.getRecords());
    }


    @Test
    public void findByid(){
        ArticleVO articleVO = articleService.findById(18L);
        System.out.println(articleVO);
        
    }

}