package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.model.converter.LabelConverter;
import com.blog.model.entity.ArticleLabel;
import com.blog.model.entity.Label;
import com.blog.mapper.LabelMapper;
import com.blog.model.form.LabelForm;
import com.blog.model.bean.R;
import com.blog.service.ArticleLabelService;
import com.blog.service.LabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Autowired
    protected ArticleLabelService articleLabelService;

    @Override
    public R deleteLabelById(Long id) {
        Label label = super.getById(id);
        QueryWrapper<ArticleLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ArticleLabel::getLabelId, id);
        int count = articleLabelService.count(queryWrapper);
        if (count > 0) {

            return R.error(label.getName() + "标签被其他文章引用,无法删除");
        }
        boolean flag = super.removeById(id);
        if (flag) {
            return R.ok();
        }
        return R.error();
    }

    @Override
    public R updateLabel(LabelForm labelForm) {
        Label label = LabelConverter.formToObj(labelForm);
        label.setUpdateDate(LocalDateTime.now());
        boolean flag = super.updateById(label);
        if (flag) {
            return R.ok();
        }
        return R.error();
    }

    @Override
    public R addLabel(LabelForm labelForm) {
        Label label = LabelConverter.formToObj(labelForm);
        label.setUpdateDate(LocalDateTime.now());
        label.setCreateDate(LocalDateTime.now());
        boolean flag = super.save(label);
        if (flag) {
            return R.ok();
        }
        return R.error();
    }


    @Override
    public R<List<Long>> saveByName(Long channelId, String names) {
        boolean flag = false;
        String[] tags = names.split(",");
        List<Long> tagIds = new ArrayList<>();
        for (String tag : tags) {
            QueryWrapper<Label> labelQueryWrapper = new QueryWrapper<>();
            labelQueryWrapper.lambda().eq(Label::getName, tag);
            Label label = this.getOne(labelQueryWrapper);
            if (label == null) {
                label = new Label();
                label.setName(tag);
                label.setChannelId(channelId);
                flag = this.save(label);
                if (!flag) {
                    return R.error();
                }
                tagIds.add(label.getId());
            }else{
                label.setChannelId(channelId);
                label.setUpdateDate(LocalDateTime.now());
                flag = this.updateById(label);
                if (!flag) {
                    return R.error();
                }
                tagIds.add(label.getId());
            }
        }
        return R.okT(tagIds);
    }
}
