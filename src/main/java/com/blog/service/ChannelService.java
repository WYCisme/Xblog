package com.blog.service;

import com.blog.model.entity.Channel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.vo.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengxin
 * @since 2019-04-28
 */
public interface ChannelService extends IService<Channel> {


    /**
     * 保存频道
     * @param name
     * @return
     */
    public R saveByName(String name);

}
