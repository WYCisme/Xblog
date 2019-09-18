/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.blog.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.job.entity.ScheduleJob;

import java.util.List;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobService extends IService<ScheduleJob> {

	/**
	 * 保存定时任务
	 */
	void saveJob(ScheduleJob scheduleJob);
	
	/**
	 * 更新定时任务
	 */
	void update(ScheduleJob scheduleJob);
	
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(List<Long> jobIds);
	
	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(List<Long> jobIds, int status);
	
	/**
	 * 立即执行
	 */
	void run(List<Long> jobIds);
	
	/**
	 * 暂停运行
	 */
	void pause(List<Long> jobIds);
	
	/**
	 * 恢复运行
	 */
	void resume(List<Long> jobIds);
}
