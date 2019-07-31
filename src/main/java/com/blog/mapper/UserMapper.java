package com.blog.mapper;

import com.blog.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengxin
 * @since 2019-04-23
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select(" select * from user where username= #{loginname} and password = #{password} ")
    User login(@Param("loginname") String loginname, @Param("password") String password);
}
