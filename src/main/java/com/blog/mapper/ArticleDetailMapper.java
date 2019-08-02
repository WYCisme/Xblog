package com.blog.mapper;

import com.blog.model.entity.ArticleDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengxin
 * @since 2019-08-02
 */
@Repository
public interface ArticleDetailMapper extends BaseMapper<ArticleDetail> {


    @Update({"<script>", "  update article_detail set view_count = view_count + 1 where article_id in  ",
        " <foreach collection='ids' item='item' index='index' open='(' separator=',' close=')' > ", "#{item}",
        "</foreach> ", "</script>"})
    int updateArticleViewCount(@Param("ids") List<Long> ids);

    @Update({"<script>", "  update article_detail set up_count = up_count + 1 where article_id in  ",
        " <foreach collection='ids' item='item' index='index' open='(' separator=',' close=')' > ", "#{item}",
        "</foreach> ", "</script>"})
    int updateArticleUpCount(@Param("ids") List<Long> ids);

    @Update({"<script>", "  update article_detail set down_count = down_count + 1 where article_id in  ",
        " <foreach collection='ids' item='item' index='index' open='(' separator=',' close=')' > ", "#{item}",
        "</foreach> ", "</script>"})
    int updateArticleDownCount(@Param("ids") List<Long> ids);

}
