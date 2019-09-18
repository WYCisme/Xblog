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
import com.blog.common.annotation.SysLog;
import com.blog.common.utils.ValidatorUtils;
import com.blog.job.entity.ScheduleJob;
import com.blog.job.entity.ScheduleJob.ScheduleGroup;
import com.blog.job.service.ScheduleJobService;
import com.blog.model.bean.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 定时任务
 */
@RestController
@RequestMapping("/back/schedule")
@Slf4j
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @GetMapping
    @RequiresPermissions("schedule:list")
    public R list(@RequestParam(defaultValue = "0") Integer page,
                  @RequestParam(defaultValue = "10") Integer size, @ModelAttribute ScheduleJob scheduleJob) {
        QueryWrapper<ScheduleJob> queryWrapper = new QueryWrapper<ScheduleJob>();
        if (StringUtils.isNotBlank(scheduleJob.getBeanName())) {
            queryWrapper.lambda().like(ScheduleJob::getBeanName, scheduleJob.getBeanName());
        }
        IPage<ScheduleJob> pages = scheduleJobService.page(new Page<>(page, size), queryWrapper);
        return R.page(pages);
    }

    /**
     * 定时任务信息
     */
    @GetMapping("/{jobId}")
    @RequiresPermissions("back:schedule:info")
    public R info(@PathVariable("jobId") Long jobId) {
        ScheduleJob schedule = scheduleJobService.getById(jobId);

        return R.okT(schedule);
    }

    /**
     * 保存定时任务
     */
    @SysLog("保存定时任务")
    @PostMapping
    @RequiresPermissions("schedule:save")
    public R save(ScheduleJob scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob, ScheduleGroup.class);
        scheduleJob.setStatus(1);
        scheduleJob.setCreateDate(LocalDateTime.now());

        scheduleJobService.save(scheduleJob);
        return R.ok();
    }


    /**
     * 修改定时任务
     */
    @SysLog("修改定时任务")
    @PutMapping
    @RequiresPermissions("schedule:update")
    public R update(ScheduleJob scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob, ScheduleGroup.class);

        ScheduleJob scheduleJobDB = scheduleJobService.getById(scheduleJob.getId());
        scheduleJobDB.setBeanName(scheduleJob.getBeanName());
        scheduleJobDB.setCronExpression(scheduleJob.getCronExpression());
        scheduleJobDB.setParams(scheduleJob.getParams());
        scheduleJobDB.setRemark(scheduleJob.getRemark());

        scheduleJobService.update(scheduleJobDB);

        return R.ok();
    }

    /**
     * 删除定时任务
     */
    @SysLog("删除定时任务")
    @DeleteMapping("/{ids}")
    @RequiresPermissions("schedule:delete")
    public R delete(@PathVariable("ids") String ids) {
        String[] idArray = ids.split(",");
        scheduleJobService.deleteBatch(Arrays.asList(idArray).stream().map(((s -> Long.parseLong(s))))
                .collect(Collectors.toList()));

        return R.ok();
    }

    /**
     * 立即执行任务
     */
    @SysLog("立即执行任务")
    @PostMapping("/{ids}/run")
    @RequiresPermissions("schedule:run")
    public R run(@PathVariable("ids") String ids) {
        scheduleJobService.run(Arrays.asList(ids.split(",")).stream().map(((s -> Long.parseLong(s))))
                .collect(Collectors.toList()));

        return R.ok();
    }

    /**
     * 暂停定时任务
     */
    @SysLog("暂停定时任务")
    @PostMapping("/{ids}/pause")
    @RequiresPermissions("schedule:pause")
    public R pause(@PathVariable("ids") String ids) {
        scheduleJobService.pause(Arrays.asList(ids.split(",")).stream().map(((s -> Long.parseLong(s))))
                .collect(Collectors.toList()));

        return R.ok();
    }

    /**
     * 恢复定时任务
     */
    @SysLog("恢复定时任务")
    @PostMapping("/{ids}/resume")
    @RequiresPermissions("schedule:resume")
    public R resume(@RequestBody String ids) {
        scheduleJobService.resume(Arrays.asList(ids.split(",")).stream().map(((s -> Long.parseLong(s))))
                .collect(Collectors.toList()));

        return R.ok();
    }

}
