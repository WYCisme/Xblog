package com.blog.mapper.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.model.vo.ArticleVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 文章类
 *
 * @author zx
 * @date 2019/5/30
 */
public class ArticleDynaSqlProvider {

    public String pageFrontArticleVO(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT(
                    "   a.id , a.title ,a.admin_id , admin.username as adminName , a.channel , a.labels , a.content , a.status, a.view_count , a.up_count  ,a.down_count , a.create_date , a.update_time ,a.images ");
                FROM(" article a ");
                LEFT_OUTER_JOIN(" admin admin on a.admin_id = admin.id  ");

                if (params.get("articleVO") != null) {
                    ArticleVO articleVO = (ArticleVO)params.get("articleVO");
                    if (StringUtils.isNotBlank(articleVO.getTitle())) {
                        WHERE(" a.title like concat('%' , #{articleVO.title} ,'%')   ");
                    }

                    if (StringUtils.isNotBlank(articleVO.getLabels())) {
                        WHERE(" a.labels like concat('%' , #{articleVO.labels} ,'%')   ");
                    }

                    if (StringUtils.isNotBlank(articleVO.getSort())) {
                        ORDER_BY(articleVO.getSort());
                    }
                }
            }
        }.toString();
        if (params.get("page") != null && params.get("size") != null) {
            sql += " limit #{page} , #{size} ";
        }
        return sql;
    }

    public String count(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT(" count(1) ");
                FROM(" article a ");
                LEFT_OUTER_JOIN(" admin admin on a.admin_id = admin.id  ");

                if (params.get("articleVO") != null) {
                    ArticleVO articleVO = (ArticleVO)params.get("articleVO");
                    if (StringUtils.isNotBlank(articleVO.getTitle())) {
                        WHERE(" a.title like concat('%' , #{articleVO.title} ,'%')   ");
                    }

                    if (StringUtils.isNotBlank(articleVO.getLabels())) {
                        WHERE(" a.labels like concat('%' , #{articleVO.labels} ,'%')   ");
                    }

                    if (StringUtils.isNotBlank(articleVO.getSort())) {
                        ORDER_BY(articleVO.getSort());
                    }
                }
            }
        }.toString();
        return sql;
    }

}
