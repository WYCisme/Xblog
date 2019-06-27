package com.blog.model.converter;

import org.springframework.beans.BeanUtils;

import com.blog.model.entity.Label;
import com.blog.model.form.LabelForm;

/**
 * 标签类转换
 *
 * @author Avalon
 * @date 2019/1/30
 */
public class LabelConverter {

    public static Label form2label(LabelForm labelForm) {
        Label label = new Label();
        BeanUtils.copyProperties(labelForm, label);
        return label;
    }

    public static Label form2label(LabelForm labelForm, Label label) {
        BeanUtils.copyProperties(labelForm, label);
        return label;
    }
}
