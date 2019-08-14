/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.blog.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.job.mapper.ScheduleJobMapper;
import com.blog.job.entity.ScheduleJob;
import com.blog.job.service.IScheduleJobService;
import com.blog.job.utils.ScheduleUtils;
import com.blog.model.enums.ScheduleStatus;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements IScheduleJobService
{

	@Resource
    private Scheduler scheduler;
	
	/**
	 * 项目启动时，初始化定时器
	 */
//	@PostConstruct
//	public void init(){
//		List<ScheduleJob> scheduleJobList = this.list();
//		for(ScheduleJob scheduleJob : scheduleJobList){
//			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
//            //如果不存在，则创建
//            try
//            {
//                if(cronTrigger == null) {
//                    ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
//                }else {
//                    ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
//                }
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//            finally
//            {
//
//            }
//        }
//	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveJob(ScheduleJob scheduleJob) {
		scheduleJob.setCreateDate(LocalDateTime.now());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        this.save(scheduleJob);
        
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
                
        this.updateById(scheduleJob);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<Long> jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
    	}
    	
    	//删除数据
    	this.removeByIds(jobIds);
	}

	@Override
    public int updateBatch(Long[] jobIds, int status){
    	Map<String, Object> map = new HashMap<>(2);
    	map.put("list", jobIds);
    	map.put("status", status);
    	return baseMapper.updateBatch(map);
    }
    
	@Override
	@Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.run(scheduler, this.getById(jobId));
    	}
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}
        
    	updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }
    
}
