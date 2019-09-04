package com.blog.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.config.shiro.ShiroUtils;
import com.blog.common.utils.ValidatorUtils;
import com.blog.controller.base.BaseController;
import com.blog.model.bean.R;
import com.blog.model.entity.Admin;
import com.blog.model.entity.Article;
import com.blog.model.enums.ArticleStatus;
import com.blog.model.form.ArticleForm;
import com.blog.model.form.ArticleForm.ArticleGroup;
import com.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * <p>
 * 文章前端控制器
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@RestController
@Slf4j
@RequestMapping("/back/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查询数据
     *
     * @param pageIndex
     * @param pageSize
     * @param article
     * @return
     */
//    @RequiresPermissions("article:list")
    @GetMapping()
    public R list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(
            defaultValue = "10") Integer limit, @ModelAttribute Article article) {
        ModelAndView modelAndView = new ModelAndView("/back/article/article-list");
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        IPage<Article> pages = article.selectPage(new Page<>(page, limit), null);
        return R.page(pages);
    }

    /**
     * 删除文章数据
     *
     * @param ids
     * @return
     */
//    @RequiresPermissions("article:del")
    @DeleteMapping(value = "/{ids}")
    public R removeArticle(@PathVariable("ids") String ids) {
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
     * 获取一个对象
     *
     * @return
     */
//    @RequiresPermissions("article:edit")
    @GetMapping("/{id}")
    public R get(@PathVariable("id") Long id) {
        Article article = articleService.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("article", article);
        jsonObject.put("em", ArticleStatus.values());
        return R.okT(jsonObject);
    }

    /**
     * 更新文章数据
     *
     * @param article
     * @return
     */
//    @RequiresPermissions("article:edit")
    @PutMapping()
    public R edit(@ModelAttribute ArticleForm articleForm) {
        ValidatorUtils.validateEntity(articleForm, ArticleGroup.class);
        return articleService.updateById(articleForm);
    }


    /**
     * 添加文章数据
     *
     * @param articleForm
     * @return
     */
//    @RequiresPermissions("article:save")
    @PostMapping()
    public R addArticle(@ModelAttribute ArticleForm articleForm) {
        ValidatorUtils.validateEntity(articleForm, ArticleGroup.class);
        Admin admin = ShiroUtils.getUserEntity();
        articleForm.setAdminId(admin.getId());
        return articleService.save(articleForm);
    }

}
