package com.blog.controller.back;

import javax.validation.Valid;

import com.blog.common.config.shiro.ShiroUtils;
import com.blog.model.entity.Admin;
import com.blog.model.enums.ArticleStatus;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.controller.base.BaseController;
import com.blog.model.entity.Article;
import com.blog.model.form.ArticleForm;
import com.blog.model.bean.R;
import com.blog.service.ArticleService;

/**
 * <p>
 * 文章前端控制器
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Controller
@Slf4j
@RequestMapping("/back/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    /**
     * 文章操作首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "back/article/list";
    }

    /**
     * 查询数据
     *
     * @param pageIndex
     * @param pageSize
     * @param article
     * @return
     */
//    @RequiresPermissions("article:list")
    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(
        defaultValue = "10") Integer limit, @ModelAttribute Article article) {
        ModelAndView modelAndView = new ModelAndView("/back/article/list");
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        IPage<Article> pages = article.selectPage(new Page<>(page, limit), null);
        modelAndView.addObject("pages",pages);
        return modelAndView;
    }

    /**
     * 删除文章数据
     *
     * @param ids
     * @param modelAndView
     * @return
     */
//    @RequiresPermissions("article:del")
    @RequestMapping(value = "{ids}/removeArticle")
    public @ResponseBody R removeArticle(@PathVariable("ids") String ids, ModelAndView modelAndView) {
        String[] idArray = ids.split(",");
        boolean flag = true;
        for (String s : idArray) {
            boolean result = articleService.removeById(NumberUtils.toLong(s));
            if (!result) {
                flag = false;
            }
        }
        if (flag) {
            return R.ok("删除成功");
        }
        return R.error("删除失败!");
    }

    /**
     * 进入更新
     *
     * @return
     */
//    @RequiresPermissions("article:edit")
    @GetMapping("{id}/toEditArticle")
    public ModelAndView toEditArticle(@PathVariable("id") Long id, ModelAndView modelAndView) {
        Article article = articleService.getById(id);
        modelAndView.addObject("article", article);
        modelAndView.addObject("em",ArticleStatus.values());
        modelAndView.setViewName("/back/article/toEditArticle");
        return modelAndView;
    }

    /**
     * 更新文章数据
     *
     * @param flag
     * @param article
     * @param modelAndView
     * @return
     */
//    @RequiresPermissions("article:edit")
    @RequestMapping(value = "/editArticle")
    public ModelAndView editArticle(String flag, @ModelAttribute @Valid ArticleForm articleForm,
        ModelAndView modelAndView, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 修改文章 ] 参数不正确 , articleForm ={} ", articleForm);
            modelAndView.setViewName("forward:/back/article/toAddArticle");
            modelAndView.addObject("message", "参数不正确");
            return modelAndView;
        }
        R result = articleService.updateById(articleForm);
        modelAndView.setViewName("/back/article/editArticle");
        modelAndView.addObject("message", result.getMsg());
        return modelAndView;
    }

    /**
     * 进入添加
     *
     * @return
     */
//    @RequiresPermissions("article:add")
    @GetMapping("/toAddArticle")
    public String toAddArticle(Model model) {
        model.addAttribute("em",ArticleStatus.values());
        return "/back/article/toAddArticle";
    }

    /**
     * 添加文章数据
     *
     * @param flag
     * @param articleForm
     * @param modelAndView
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
//    @RequiresPermissions("article:add")
    @PostMapping(value = "/addArticle")
    public ModelAndView addArticle(@ModelAttribute @Valid ArticleForm articleForm,
         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            log.error(" [ 添加文章 ] 参数不正确 , articleForm ={} ", articleForm);
            modelAndView.setViewName("forward:/back/article/toAddArticle");
            modelAndView.addObject("message", "参数不正确");
            return modelAndView;
        }
        Admin admin = ShiroUtils.getUserEntity();
        articleForm.setAdminId(admin.getId());

        R result = articleService.save(articleForm);
        modelAndView.setViewName("forward:/back/article/index");
        modelAndView.addObject("message", result.getMsg());
        return modelAndView;
    }

}
