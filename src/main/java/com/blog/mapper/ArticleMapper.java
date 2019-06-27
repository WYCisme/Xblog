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

    @Select({ " select  a.id , a.title ,a.admin_id , admin.username as adminName , a.channel , a.labels , a.content , a.status, a.view_count , a.up_count  ,a.down_count , a.create_date , a.update_time ,a.images ",
            " from article a left join  admin admin on a.admin_id = admin.id  ",
        "  ${ew.customSqlSegment} "
    })
    List<ArticleVO> test(Page<ArticleVO> page,@Param(Constants.WRAPPER) Wrapper wrapper);


    @Select(" select  a.id , a.title ,a.admin_id , admin.username as adminName , a.channel , a.labels , a.content , a.status, a.view_count , a.up_count  ,a.down_count , a.create_date , a.update_time ,a.images "
        + " from article a left join admin admin  on a.admin_id = admin.id where a.id = #{id} ")
    ArticleVO getOne(@Param("id") Long id);

    @Update({"<script>", "  update article set view_count = view_count + 1 where id in  ",
        " <foreach collection='ids' item='item' index='index' open='(' separator=',' close=')' > ", "#{item}",
        "</foreach> ", "</script>"})
    int updateArticleViewCount(@Param("ids") List<Long> ids);

    @Update({"<script>", "  update article set up_count = up_count + 1 where id in  ",
            " <foreach collection='ids' item='item' index='index' open='(' separator=',' close=')' > ", "#{item}",
            "</foreach> ", "</script>"})
    int updateArticleUpCount(@Param("ids") List<Long> ids);

    @Update({"<script>", "  update article set down_count = down_count + 1 where id in  ",
            " <foreach collection='ids' item='item' index='index' open='(' separator=',' close=')' > ", "#{item}",
            "</foreach> ", "</script>"})
    int updateArticleDownCount(@Param("ids") List<Long> ids);

}
