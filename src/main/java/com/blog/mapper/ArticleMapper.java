package com.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.mapper.provider.ArticleDynaSqlProvider;
import com.blog.model.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.model.vo.ArticleVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {


    @SelectProvider(type = ArticleDynaSqlProvider.class,method = "pageFrontArticleVO")
    List<ArticleVO> pageFrontArticleVO(Map<String,Object> params);

    @SelectProvider(type = ArticleDynaSqlProvider.class,method = "count")
    Long count(Map<String,Object> params);


    @Select(" select  a.id , a.title ,a.admin_id , admin.username as adminName , a.channel , a.labels , a.content , a.status, ad.view_count , ad.up_count  ,ad.down_count , a.create_date , a.update_date ,a.images "
        + " from article a left join admin admin  on a.admin_id = admin.id left join article_detail ad on ad.article_id = a.id where a.id = #{id} ")
    ArticleVO getOne(@Param("id") Long id);

}
