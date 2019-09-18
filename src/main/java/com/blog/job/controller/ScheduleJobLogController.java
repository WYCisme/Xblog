/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.blog.job.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.job.entity.ScheduleJobLog;
import com.blog.job.service.ScheduleJobLogService;
import com.blog.model.bean.R;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @GetMapping("/")
    @RequiresPermissions("schedule:log:list")
    public R list(@RequestParam Map<String, Object> params) {
        QueryWrapper<ScheduleJobLog> queryWrapper = new QueryWrapper<>();
        IPage permissionVOPage = scheduleJobLogService.page(new Page<>(NumberUtils.toLong(params.get("page").toString(), 1), NumberUtils.toLong(params.get("limit").toString(), 10)), queryWrapper);
        return R.page(permissionVOPage);
    }

    /**
     * 定时任务日志信息
     */
    @GetMapping("/{logId}")
    @RequiresPermissions("schedule:log:list")
    public R info(@PathVariable("logId") Long logId) {
        ScheduleJobLog log = scheduleJobLogService.getById(logId);

        return R.okT(log);
    }
}
