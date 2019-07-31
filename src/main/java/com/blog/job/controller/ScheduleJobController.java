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
import com.blog.common.utils.ValidatorUtils;
import com.blog.job.entity.ScheduleJob;
import com.blog.job.service.IScheduleJobService;
import com.blog.model.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

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
//	@RequiresPermissions("sys:schedule:list")
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
	@RequiresPermissions("sys:schedule:info")
	public R info(@PathVariable("jobId") Long jobId){
		ScheduleJob schedule = scheduleJobService.getById(jobId);
		
		return R.okT(schedule);
	}

    /**
     * 进入添加
     *
     * @return
     */
	@RequestMapping("/to/save")
	public String toSave(){
	    return "/back/schedule/schedule-save";
    }
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@PostMapping("/save")
//	@RequiresPermissions("sys:schedule:save")
	public @ResponseBody R save(@RequestBody ScheduleJob scheduleJob,
        BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            log.error(" [ 修改用户 ] 参数不正确 , adminForm ={} ", scheduleJob);
            return R.error(bindingResult.getFieldError().toString());
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
//	@RequiresPermissions("sys:schedule:update")
	public R update(@ModelAttribute @Valid ScheduleJob scheduleJob,
        BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            log.error(" [ 修改用户 ] 参数不正确 , adminForm ={} ", scheduleJob);
            return R.error(bindingResult.getFieldError().toString());
        }
        scheduleJob.setStatus(1);
				
		scheduleJobService.update(scheduleJob);
		
		return R.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@RequestMapping("/delete")
//	@RequiresPermissions("sys:schedule:delete")
	public R delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@RequestMapping("/run")
//	@RequiresPermissions("sys:schedule:run")
	public R run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@RequestMapping("/pause")
//	@RequiresPermissions("sys:schedule:pause")
	public R pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@RequestMapping("/resume")
//	@RequiresPermissions("sys:schedule:resume")
	public R resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return R.ok();
	}

}
