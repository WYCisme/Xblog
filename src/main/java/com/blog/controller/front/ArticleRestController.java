package com.blog.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.annotation.SysLog;
import com.blog.controller.base.FrontBaseController;
import com.blog.model.converter.ArticleConverter;
import com.blog.model.entity.Admin;
import com.blog.model.entity.Article;
import com.blog.model.form.ArticleForm;
import com.blog.model.dto.ArticleDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.blog.model.bean.R;
import com.blog.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章前端控制器, 用于VSCODE编辑器
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@RestController
@Slf4j
@RequestMapping("/article")
public class ArticleRestController extends FrontBaseController
{

    @Autowired
    private ArticleService articleService;


    /**
     * 添加文章数据
     *
     * @param articleDTO
     * @return
     */
    @PostMapping(value = "/public")
    @SysLog("添加文章数据")
    public R addArticle(ArticleDTO articleDTO)
    {
        // 获取用户
        R r = getAdminByAccessToken();
        if (r.getCode() < 1)
        {
            r.setData(articleDTO);

            return r;
        }
        articleDTO.setAdminId(((Admin)r.getData()).getId());
        // 如果频道或者标签是空, 则直接对文章进行保存
        if (StringUtils.isBlank(articleDTO.getSubmitToken()) && (StringUtils.isBlank(articleDTO.getChannel())
            || StringUtils.isBlank(articleDTO.getLabels())))
        {
            r = articleService.save(articleDTO);
            articleDTO.setSubmitToken(r.getData() + "");
            r.setData(articleDTO);

            return r;
        }
        if (StringUtils.isNotBlank(articleDTO.getSubmitToken()) && (StringUtils.isBlank(articleDTO.getChannel())
            || StringUtils.isBlank(articleDTO.getLabels())))
        {
            r = articleService.updateBySign(articleDTO);
            r.setData(articleDTO);

            return r;
        }
        // 频道,标签不是空
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Article::getSubmitToken, articleDTO.getSubmitToken());
        Article article = articleService.getOne(queryWrapper);
        if (article == null)
        {
            // 新增文章
            ArticleForm articleForm = ArticleConverter.dtoToForm(articleDTO);
            r = articleService.save(articleForm);
            articleDTO.setSubmitToken(r.getData() + "");
            r.setData(articleDTO);
        }
        else
        {
            // 修改
            ArticleForm articleForm = ArticleConverter.dtoToForm(articleDTO);
            r = articleService.updateById(articleForm);
        }
        r.setData(articleDTO);
        return r;
    }

    /**
     * 搜索文章数据
     *
     * @return
     */
    @PostMapping(value = "/search")
    @SysLog("搜索文章数据")
    public R searchArticle(ArticleDTO articleDTO)
    {

        // 获取用户
        R r = getAdminByAccessToken();
        if (r.getCode() < 1)
        {
            r.setData(articleDTO);

            return r;
        }
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(articleDTO.getTitle()))
        {
            articleQueryWrapper.lambda().like(Article::getTitle, articleDTO.getTitle());
        }
        IPage<Article> page = articleService.page(new Page<>(1, 10), articleQueryWrapper);
        List<ArticleDTO> articleVOList = new ArrayList<>();
        if (page.getRecords().size() > 0)
        {
            for (Article article : page.getRecords())
            {
                articleVOList.add(ArticleConverter.objTodto(article));
            }
        }
        return R.ok("查询成功!", articleVOList);
    }

//    @PostMapping(value = "/search2")
//    @SysLog("搜索文章数据")
//    public R test(ArticleDTO articleDTO)
//    {
//        SearchQuery searchQuery =
//            new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("title", articleDTO.getTitle())).build();
//        List<Article> list = elasticsearchTemplate.queryForList(searchQuery, Article.class);
//        List<ArticleDTO> articleVOList = new ArrayList<>();
//        if (articleVOList.size() > 0)
//        {
//            for (Article article : list)
//            {
//                articleVOList.add(ArticleConverter.objTodto(article));
//            }
//        }
//        return R.ok("查询成功!", articleVOList);
//    }

}
