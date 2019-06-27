package com.blog.mapper;

import com.blog.model.entity.SystemSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统设置表 Mapper 接口
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Mapper
@Repository
public interface SystemSettingMapper extends BaseMapper<SystemSetting> {


    @Update(" update system_setting set value = #{value} where key=#{key} ")
    int updateValueByKey(String key, String value);

    @Select(" select value from system_setting where key=#{key} ")
    String selectValueByKey(String key);
}
