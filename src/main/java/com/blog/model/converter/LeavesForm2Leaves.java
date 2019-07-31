package com.blog.model.converter;

import org.springframework.beans.BeanUtils;

import com.leave.model.entity.Leaves;
import com.leave.model.form.LeavesForm;

/**
 * 转换类
 * 
 * @author zx
 * @date 2019/4/24
 */
public class LeavesForm2Leaves {

    public static Leaves converter(LeavesForm leaveForm) {
        Leaves leave = new Leaves();
        BeanUtils.copyProperties(leaveForm, leave);
        return leave;
    }

    public static Leaves converter(LeavesForm leaveForm, Leaves leave) {
        BeanUtils.copyProperties(leaveForm, leave);
        return leave;
    }
}
