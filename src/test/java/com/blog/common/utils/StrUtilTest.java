package com.blog.common.utils;

import com.blog.BaseUtil;
import com.blog.model.entity.Article;
import org.junit.Test;

import java.sql.Struct;

import static org.junit.Assert.*;

/**
 * @author zx
 * @date 2019/5/4
 */
public class StrUtilTest extends BaseUtil {

    @Test
    public void findImageByContent() {
        Article article = new Article().selectById(11);
        String a = StrUtil.findImageByContent(article.getContent());
        System.out.println("a: "+a);
    }
}