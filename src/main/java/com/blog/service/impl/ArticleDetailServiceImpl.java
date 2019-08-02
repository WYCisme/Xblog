package com.blog.service.impl;

import com.blog.model.bean.R;
import com.blog.model.entity.ArticleDetail;
import com.blog.mapper.ArticleDetailMapper;
import com.blog.service.ArticleDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengxin
 * @since 2019-08-02
 */
@Service
public class ArticleDetailServiceImpl extends ServiceImpl<ArticleDetailMapper, ArticleDetail> implements ArticleDetailService {

    @Autowired
    protected ArticleDetailMapper articleDetailMapper;

    @Override
    public R updateViewCount(List<Long> ids) {
        if(ids == null || ids.isEmpty()){
            return R.error("IDS为空!");
        }

        int row =  articleDetailMapper.updateArticleViewCount(ids);
        if(row <= 0){
            return R.error("更新文章浏览量失败");
        }

        return R.ok("成功!");
    }

    @Override
    public R updateUpCount(List<Long> ids) {
        int row =  articleDetailMapper.updateArticleUpCount(ids);
        if(row <= 0){
            return R.error("更新点赞失败");
        }

        return R.ok("成功!");
    }

    @Override
    public R updateDownCount(List<Long> ids) {
        int row =  articleDetailMapper.updateArticleDownCount(ids);
        if(row <= 0){
            return R.error("更新踩失败");
        }

        return R.ok("成功!");
    }

    @Override
    public R save(Long articleId)
    {
        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setArticleId(articleId);
        boolean flag = super.save(articleDetail);
        if(!flag){
            return  R.error("保存文章失败!");
        }
        return R.ok("保存文章成功!");
    }
}
