package com.blog.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.model.entity.Label;
import com.blog.model.form.LabelForm;
import com.blog.model.bean.R;
import com.blog.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Controller
@Slf4j
@RequestMapping("/back/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 标签操作首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "back/label/listLabel";
    }

    /**
     * 查询数据
     *
     * @param pageIndex
     * @param pageSize
     * @param label
     * @return
     */
    @RequiresPermissions("label:list")
    @RequestMapping(value = "/listLabel")
    public @ResponseBody R listLabel(@RequestParam(value = "permissionVOPage", defaultValue = "1") Integer page, @RequestParam(
        defaultValue = "10") Integer limit, @ModelAttribute Label label) {
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Label::getName, label.getName());
        IPage<Label> pages = label.selectPage(new Page<>(page, limit), queryWrapper);
        return R.page(pages);
    }

    /**
     * 删除标签数据
     *
     * @param ids
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("label:del")
    @RequestMapping(value = "{ids}/removeLabel")
    public @ResponseBody R removeLabel(@PathVariable("ids") String ids, ModelAndView modelAndView) {
        String[] idArray = ids.split(",");
        boolean flag = true;
        for (String s : idArray) {
            R result = labelService.deleteLabelById(NumberUtils.toLong(s));
            if (result.getCode() <= 0) {
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
    @RequiresPermissions("label:edit")
    @GetMapping("{id}/toEditLabel")
    public ModelAndView toEditLabel(@PathVariable("id") Long id, ModelAndView modelAndView) {
        Label label = labelService.getById(id);
        modelAndView.addObject("label", label);
        modelAndView.setViewName("back/label/toEditLabel");
        return modelAndView;
    }

    /**
     * 更新标签数据
     *
     * @param flag
     * @param label
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("label:edit")
    @RequestMapping(value = "/editLabel")
    public ModelAndView editLabel(@ModelAttribute @Valid LabelForm labelForm, ModelAndView modelAndView,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 修改标签 ] 参数不正确 , labelForm ={} ", labelForm);
            modelAndView.setViewName("forward:/back/label/toAddLabel");
            modelAndView.addObject("message", "参数不正确");
            return modelAndView;
        }
        R result = labelService.updateLabel(labelForm);
        modelAndView.setViewName("forward:/back/label/index");
        modelAndView.addObject("message", result.getMsg());
        return modelAndView;
    }

    /**
     * 进入添加
     *
     * @return
     */
    @RequiresPermissions("label:save")
    @GetMapping("/toAddLabel")
    public ModelAndView toAddLabel() {
        return new ModelAndView("back/label/toAddLabel");
    }

    /**
     * 添加标签数据
     *
     * @param label
     * @param modelAndView
     * @return
     */
    @RequiresPermissions("label:save")
    @PostMapping(value = "/addLabel")
    public ModelAndView addLabel(@ModelAttribute @Valid LabelForm labelForm, ModelAndView modelAndView,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error(" [ 添加标签 ] 参数不正确 , labelForm ={} ", labelForm);
            modelAndView.setViewName("forward:/back/label/toAddLabel");
            modelAndView.addObject("message", "参数不正确");
            return modelAndView;
        }
        R result = labelService.addLabel(labelForm);
        modelAndView.setViewName("forward:/back/label/index");
        modelAndView.addObject("message", result.getMsg());
        return modelAndView;
    }
}
