/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.blog.job.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.annotation.SysLog;
import com.blog.job.entity.ScheduleJob;
import com.blog.job.service.IScheduleJobService;
import com.blog.model.bean.R;
import com.mchange.lang.LongUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.xpath.operations.Mod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定时任务
 *
 */
@Controller
@RequestMapping("/back/schedule")
@Slf4j
public class ScheduleJobController {

	@Autowired
	private IScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
//	@RequiresPermissions("back:schedule:list")
	public ModelAndView list(@RequestParam(defaultValue = "0") Integer page ,@RequestParam(defaultValue = "10") Integer limit){
        IPage<ScheduleJob> pages = scheduleJobService.page(new Page<>(page, limit));
        ModelAndView modelAndView = new ModelAndView("/back/schedule/schedule-list");
        modelAndView.addObject("pages",pages);
		return modelAndView;
	}
	
	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	@RequiresPermissions("back:schedule:info")
	public R info(@PathVariable("jobId") Long jobId){
		ScheduleJob schedule = scheduleJobService.getById(jobId);
		
		return R.okT(schedule);
	}

    /**
     * 进入添加
     *
     * @return
     */
	@GetMapping("/to/save")
	public String toSave(){
	    return "/back/schedule/schedule-save";
    }
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@PostMapping("/save")
//	@RequiresPermissions("back:schedule:save")
	public @ResponseBody R save(ScheduleJob scheduleJob,
        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error(" [ 修改用户 ] 参数不正确 , adminForm ={} ", scheduleJob);
            return R.error("保存失败!" + bindingResult.toString());
        }
        scheduleJob.setStatus(1);
        scheduleJob.setCreateTime(new Date());

		scheduleJobService.save(scheduleJob);
		return R.ok("添加成功");
	}

    /**
     * 进入修改
     *
     * @return
     */
    @GetMapping("/{id}/to/update")
    public String toUpdate(@PathVariable("id") Long id,Model model){
        ScheduleJob scheduleJob = scheduleJobService.getById(id);
        model.addAttribute("scheduleJob",scheduleJob);

        return "/back/schedule/schedule-update";
    }

	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@PostMapping("/update")
//	@RequiresPermissions("back:schedule:update")
	public @ResponseBody R update(@ModelAttribute @Valid ScheduleJob scheduleJob,
        BindingResult bindingResult){


	    System.out.println(bindingResult.toString());
        if (bindingResult.hasErrors()) {
            log.error(" [ 修改用户 ] 参数不正确 , adminForm ={} ", scheduleJob);
            return R.error(bindingResult.toString());
        }

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
	@PostMapping("/delete")
//	@RequiresPermissions("back:schedule:delete")
	public R delete(Long[] jobIds){
		scheduleJobService.deleteBatch(null);
		
		return R.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@RequestMapping("/run")
//	@RequiresPermissions("back:schedule:run")
	public R run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@RequestMapping("/pause")
//	@RequiresPermissions("back:schedule:pause")
	public R pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@RequestMapping("/resume")
//	@RequiresPermissions("back:schedule:resume")
	public R resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return R.ok();
	}

}
