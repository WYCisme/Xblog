//package com.blog.controller.front;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.blog.BaseUtil;
//import com.blog.es.dao.ArticleDao;
//import com.blog.model.bean.R;
//import com.blog.model.converter.ArticleConverter;
//import com.blog.model.dto.ArticleDTO;
//import com.blog.model.entity.Article;
//import com.blog.model.vo.ArticleVO;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class ArticleRestControllerTest extends BaseUtil
//{
//
////    @Autowired
////    private ElasticsearchTemplate elasticsearchTemplate;
////
////    @Autowired
////    ArticleDao articleDao;
////
////    @Test
////    public void test1()
////    {
////        SearchQuery searchQuery =
////            new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("title", "")).build();
////        org.springframework.data.domain.Page<ArticleVO> page = articleDao.search(searchQuery);
////        List<ArticleDTO> articleVOList = new ArrayList<>();
////        if (articleVOList.size() > 0)
////        {
////            for (ArticleVO article : page.getContent())
////            {
////                articleVOList.add(ArticleConverter.voTodto(article));
////            }
////        }
////        System.out.println(articleVOList.toString());
////
////    }
//
//
//}