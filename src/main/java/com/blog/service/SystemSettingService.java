package com.blog.service;

import com.blog.model.entity.SystemSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.bean.R;

import java.util.List;

/**
 * <p>
 * 系统设置表 服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
public interface SystemSettingService extends IService<SystemSetting> {

    String selectValueByKey(String key);

    R updateValueByKey(String key, String value);

    List<SystemSetting> selectAll();
}
