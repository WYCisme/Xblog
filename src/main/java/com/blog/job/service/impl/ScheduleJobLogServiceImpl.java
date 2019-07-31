/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.blog.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.job.dao.ScheduleJobLogMapper;
import com.blog.job.entity.ScheduleJobLog;
import com.blog.job.service.IScheduleJobLogService;
import org.springframework.stereotype.Service;

@Service
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements
    IScheduleJobLogService
{


}
