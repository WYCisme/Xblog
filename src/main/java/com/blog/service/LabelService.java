package com.blog.service;

import com.blog.model.entity.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.form.LabelForm;
import com.blog.model.bean.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
public interface LabelService extends IService<Label> {


    /**
     * 删除标签
     * @param id
     * @return
     */
    R deleteLabelById(Long id);


    /**
     * 修改标签
     *
     * @param labelForm
     * @return
     */
    R updateLabel(LabelForm labelForm);


    /**
     * 新增标签
     *
     * @param labelForm
     * @return
     */
    R addLabel(LabelForm labelForm);


    /**
     * 添加标签
     * @param channelId
     * @param tags
     * @return List<Long>
     */
    R<List<Long>> saveByName(Long channelId,String tags);
}
