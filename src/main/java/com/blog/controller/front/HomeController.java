package com.blog.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.annotation.SysLogAnno;
import com.blog.common.constants.AppConstants;
import com.blog.common.constants.CacheKey;
import com.blog.common.utils.CacheUtil;
import com.blog.common.utils.WebUtils;
import com.blog.controller.base.BaseController;
import com.blog.model.entity.Article;
import com.blog.model.entity.Label;
import com.blog.model.vo.ArticleVO;
import com.blog.model.vo.R;
import com.blog.service.ArticleService;
import com.blog.service.LabelService;
import com.vividsolutions.jts.operation.valid.IsValidOp;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 首页控制器
 *
 * @author avalon
 * @date 2019/4/19
 */
@RequestMapping(value = "/home")
@Controller
@Slf4j
public class HomeController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private LabelService labelService;

    /**
     * 首页
     *
     * @return
     */
    @SysLogAnno("主页")
    @RequestMapping(value = "/")
    public ModelAndView homePre(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size, @RequestParam(value = "title", defaultValue = "") String title, @RequestParam(value="labels",defaultValue = "") String labels) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("front/home/home");
        ArticleVO articleVO = new ArticleVO();
        articleVO.setTitle(title);
        articleVO.setLabels(labels);
        articleVO.setSort(" view_count desc ");

        Page<ArticleVO> pages =
                articleService.pageFrontArticleVO(new Page<ArticleVO>(page, size), articleVO);

        modelAndView.addObject("pages", R.page2(pages));
//        //添加浏览记录
//        List<Long> articleIds = pages.getRecords().stream().map(ArticleVO::getId).collect(Collectors.toList());
//        articleService.updateArticleViewCount(articleIds);
        //条件
        modelAndView.addObject("title",title);
        modelAndView.addObject("labels",labels);
        return modelAndView;
    }

    /**
     * 翻页请求文章(ajax请求用. 已经作废)
     *
     * @param page
     * @param size
     * @param title
     * @return
     */
    @Deprecated
    @RequestMapping("/article")
    public ModelAndView pageArticle(@RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size, @RequestParam(value = "title", defaultValue = "") String title, @RequestParam(value="labels",defaultValue = "") String labels) {
        ArticleVO articleVO = new ArticleVO();
        articleVO.setTitle(title);
        articleVO.setLabels(labels);
        articleVO.setSort(" view_count desc ");
        Page<ArticleVO> pages =
            articleService.pageFrontArticleVO(new Page<ArticleVO>(page, size), articleVO);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("front/home/article");
        modelAndView.addObject("pages", R.page2(pages));
        modelAndView.addObject("currPage", page);
        //添加浏览记录
        List<Long> articleIds = pages.getRecords().stream().map(ArticleVO::getId).collect(Collectors.toList());
        articleService.updateArticleViewCount(articleIds);
        //条件
        modelAndView.addObject("title",title);
        modelAndView.addObject("labels",labels);

        return modelAndView;
    }

    /**
     * 排行榜
     *
     * @return
     */
    @RequestMapping("/isView")
    public ModelAndView isView() {
        ArticleVO articleVO = new ArticleVO();
        articleVO.setSort(" view_count desc ");
        IPage<ArticleVO> pages = articleService.pageFrontArticleVO(new Page<ArticleVO>(1, 5), articleVO);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("front/home/isView");
        modelAndView.addObject("pages", R.page(pages));
        modelAndView.addObject("one", pages.getRecords().size() == 0 ? null : pages.getRecords().get(0));

        return modelAndView;
    }

    /**
     * 排行榜
     *
     * @return
     */
    @RequestMapping("/isUp")
    public ModelAndView isUp() {
        ArticleVO articleVO = new ArticleVO();
        articleVO.setSort("  up_count desc ");
        IPage<ArticleVO> pages = articleService.pageFrontArticleVO(new Page<ArticleVO>(1, 5), articleVO);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("front/home/isUp");
        modelAndView.addObject("pages", R.page(pages));
        modelAndView.addObject("one", pages.getRecords().size() == 0 ? null : pages.getRecords().get(0));

        return modelAndView;
    }

    /**
     * 标签云
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/labels")
    public ModelAndView pageLabels(@RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size) {

        Object labelsObj = cacheUtil.get(CacheUtil.TWENTYFOUR_HOURS, CacheKey.CACHE_LABELS_KEY);
        List<Label> list = null;

        if(labelsObj == null || StringUtils.isBlank(labelsObj.toString())){
            list = labelService.list();
//            for (Label label : list) {
//                QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
//                queryWrapper.lambda().like(Article::getLabels, label.getName());
//                int count = articleService.count(queryWrapper);
//                label.setName(label.getName()+"("+count+")");
//            }
            cacheUtil.set(CacheUtil.TWENTYFOUR_HOURS, CacheKey.CACHE_LABELS_KEY, JSONArray.fromObject(list).toString());
        }else{
            list = JSONArray.fromObject(labelsObj);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("front/home/labels");
        modelAndView.addObject("labels", list);
        return modelAndView;
    }


    /**
     * 时间轴
     *
     * @return
     */
    @RequestMapping("/time")
    public ModelAndView time(){
        ModelAndView modelAndView = new ModelAndView();
        IPage<ArticleVO> pages = articleService.pageFrontArticleVO(new Page<ArticleVO>(1, 99), null);
        modelAndView.setViewName("front/home/time");
        modelAndView.addObject("pages", R.page(pages));
        return modelAndView;
    }


    /**
     * 文章详细
     *
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam(defaultValue = "0") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("article", articleService.findById(id));
        modelAndView.setViewName("front/home/detail");

        //添加浏览记录
        articleService.updateArticleViewCount(Arrays.asList(id));
        return modelAndView;
    }


    /**
     * 添加点赞记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/upCount")
    public @ResponseBody R upCount(@RequestParam(value = "id",defaultValue = "0") Long id){
        //添加点赞记录
        return articleService.updateArticleUpCount(Arrays.asList(id));
    }
}
