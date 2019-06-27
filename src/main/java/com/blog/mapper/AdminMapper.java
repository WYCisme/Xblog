package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.model.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员控制类
 *
 * @author zx
 * @date 2019/2/10
 */
@Repository
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {


    @Select(" select * from  admin  where username  = #{username} and password = #{password} ")
    Admin selectByLoginnameAndPassword(@Param("username") String username, @Param("password") String password);


    @Select(" SELECT p.`permission` FROM admin_role ur  LEFT JOIN role_permission rp ON rp.`role_id` = ur.`role_id` LEFT JOIN permission p ON rp.`permission_id` = p.id WHERE ur.`admin_id` = #{id}")
    List<String> findAdminPermission(long id);

    @Select(" SELECT r.`role` FROM admin_role ar  LEFT JOIN role r ON ar.`role_id` = r.`id`  WHERE ar.`admin_id` = #{id}")
    List<String> findAdminRole(long id);



}