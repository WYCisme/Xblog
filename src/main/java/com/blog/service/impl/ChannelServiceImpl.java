package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.model.entity.Channel;
import com.blog.mapper.ChannelMapper;
import com.blog.model.entity.Label;
import com.blog.model.vo.R;
import com.blog.service.ChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengxin
 * @since 2019-04-28
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {

    @Override
    public R saveByName(String name) {
        QueryWrapper<Channel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Channel::getName, name);
        Channel channel = this.getOne(queryWrapper);
        if (channel == null) {
            channel = new Channel();
            channel.setName(name);
            boolean flag = this.save(channel);
            if (!flag) {
                return R.error();
            }
        }
        return R.okT(channel.getId());
    }
}
